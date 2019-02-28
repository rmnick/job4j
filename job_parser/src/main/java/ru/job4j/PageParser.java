package ru.job4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class PageParser implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(PageParser.class.getName());
    private Connection connection;
    private final Config config;
    private final BlockingQueue<Vacancy> vacancies;
    private final Map<String, Month> months = new HashMap<>();
    private final LocalDateTime startDate = LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0);

    /**
     * constructor
     * @param config Config
     * @param vacancies BlockingQueue<Vacancy>
     */
    public PageParser(final Config config, final BlockingQueue<Vacancy> vacancies) {
        this.config = config;
        this.vacancies = vacancies;
        try {
            this.connection = DriverManager.getConnection(config.get("url"), config.get("username"), config.get("password"));
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        fillMap();
    }

    /**
     * fill the map for searching right month
     */
    public void fillMap() {
        this.months.put("янв", Month.JANUARY);
        this.months.put("фев", Month.FEBRUARY);
        this.months.put("мар", Month.MARCH);
        this.months.put("апр", Month.APRIL);
        this.months.put("май", Month.MAY);
        this.months.put("июн", Month.JUNE);
        this.months.put("июл", Month.JULY);
        this.months.put("авг", Month.AUGUST);
        this.months.put("сен", Month.SEPTEMBER);
        this.months.put("окт", Month.OCTOBER);
        this.months.put("ноя", Month.NOVEMBER);
        this.months.put("дек", Month.DECEMBER);
    }

    /**
     * create DB
     * create and start costumer thread for uploading from queue and downloading all data to DB
     * start main thread for parsing page
     */
    public void start() {
        initDb();
        try {
            VacancyLoader vl = new VacancyLoader(this.connection, this.vacancies);
            vl.start();
            parse(checkDate());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * create table "vacancies" for saving our downloading vacancies
     */
    public void initDb() {
        String createVac = "create table if not exists vacancies(id serial primary key,"
                + "name varchar(1500) NOT NULL UNIQUE, url varchar (1500), description text, dateVac timestamp);";
        try (Statement st = connection.createStatement()) {
            st.execute(createVac);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * check date in DB and if date not null return last one, else return default date
     * @return LocalDateTime
     */
    public LocalDateTime checkDate() {
        LocalDateTime result = null;
        String select = "select max(datevac) as max from vacancies;";
        try (ResultSet rs = connection.createStatement().executeQuery(select)) {
            while (rs.next()) {
                Timestamp vacDate = rs.getTimestamp(1);
                result = (vacDate == null ? startDate : vacDate.toLocalDateTime());
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return result;
    }

    /**
     * method for parsing web page and searching Java vacancy
     * main thread
     * @param dateTime LocalDateTime
     * @throws IOException e
     */
    public void parse(LocalDateTime dateTime) throws IOException {
        Document doc = Jsoup.connect(config.get("urlPage")).get();
        /**
         * searching for how many pages in this forum
         */
        int numberOfPages = getNumberOfPages(doc);
        for (int i = 1; i <= numberOfPages; i++) {
            /**
             * flag for stopping this cycle if date of vacancy will be old
             */
            boolean flagStop = true;
            String urlPage = String.format("%s%d", config.get("urlPage"), i);
            Document page = Jsoup.connect(urlPage).get();
            Elements mainTable = page.select("table[class=forumTable]");
            Elements rows = mainTable.select("tr");
            /**
             * skip the first four empty lines
             */
            ListIterator<Element> iterator = rows.listIterator(4);
            while (iterator.hasNext()) {
                /**
                 * take columns with date
                 */
                Elements columns = iterator.next().select("td");
                /**
                 * checking date if date is wrong then out from "while" cycle
                 */
                if (!parseDate(columns.get(5).text()).isAfter(dateTime)) {
                    flagStop = false;
                    break;
                }
                /**
                 * searching for java position in current column
                 */
                if (searchJava(columns.get(1).text())) {
                    loadToQueue(columns);
                }
            }
            /**
             * out from "for" cycle according with date
             */
            if (!flagStop) {
                break;
            }
        }
        /**
         * make some "poison pill" and send to consumer
         */
        vacancies.add(new Vacancy("stop", null, null, null));
    }

    /**
     * parse, create and load vacancy to queue
     * @param columns Elements
     * @throws IOException e
     */
    public void loadToQueue(Elements columns) throws IOException {
        Element element = columns.get(1).child(0);
        LocalDateTime date = this.parseDate(columns.get(5).text());
        String name = element.text();
        String url = element.attr("href");
        Document job = Jsoup.connect(url).get();
        Element jobTable = job.select("table[class=msgTable]").get(0);
        Elements trJobTable = jobTable.select("tr");
        Elements tdJobTable = trJobTable.get(1).select("td");
        String description = tdJobTable.get(1).text();
        vacancies.add(new Vacancy(name, url, description, date));
    }

    /**
     * method for searching number of pages
     * it needs for creating our url address
     * @return int
     */
    public int getNumberOfPages(Document doc) {
        Elements elements = doc.select("table.sort_options");
        Element table = elements.get(1);
        Elements columns = table.select("td");
        Element column = columns.get(0);
        String str = column.text();
        String[] arr = str.split(" ");
        int numberOfpages = Integer.valueOf(arr[arr.length - 1]);
        return numberOfpages;
    }

    /**
     * parsing localDate from string
     * @param str String
     * @return LocalDateTime
     */
    public LocalDateTime parseDate(String str) {
        String time = str.substring(str.indexOf(",") + 2);
        int hour = new Integer(time.split(":")[0].trim());
        int min = new Integer(time.split(":")[1].trim());
        LocalTime localTime = LocalTime.of(hour, min);

        str = str.substring(0, str.indexOf(",")).trim();
        LocalDate localDate = LocalDate.now();

        if (str.contains("вчера")) {
            localDate.minusDays(1);
        } else if (!str.contains("сегодня") && !str.contains("вчера")) {
            int year = new Integer("20" + str.substring(str.length() - 2));
            String strMonth = str.substring(2, 6).trim();
            int day = new Integer(str.substring(0, 2).trim());
            localDate = LocalDate.of(year, parseMonth(strMonth), day);
        }
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * get right month from our map
     * @param str String
     * @return Month
     */
    public Month parseMonth(String str) {
        return this.months.get(str);
    }

    /**
     * search "java" word
     * @param str String
     * @return boolean
     */
    public boolean searchJava(String str) {
        str = str.toLowerCase();
        return str.contains("java") && !str.contains("script");
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }
}

