package ru.job4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.*;
import java.util.ListIterator;
import java.util.concurrent.BlockingQueue;

public class PageParser implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(PageParser.class.getName());
    private Connection connection;
    private final Config config;
    private final BlockingQueue<Vacancy> vacancies;
    private static boolean orderFlag = true;
    private Document doc;

    public PageParser(final Config config, final BlockingQueue<Vacancy> vacancies) {
        this.config = config;
        this.vacancies = vacancies;
        try {
            this.connection = DriverManager.getConnection(config.get("url"), config.get("username"), config.get("password"));
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * create table "vacancies" for saving our downloading vacancies
     */
    public void createDb() {
            String create = "create table if not exists vacancies(id serial primary key," +
                    "name varchar(1500) NOT NULL UNIQUE, url varchar (1500), description text);";
            try (Statement st = connection.createStatement()) {
                st.execute(create);
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }

    }

    public void parse() {
        try {
            LOG.info(orderFlag);
            if (orderFlag) {
                firstParse();
                LOG.info(orderFlag);
            } else {
                secondParse();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }


    public void firstParse() throws IOException {
        LOG.info("first parsing");
        createDb();

        VacancyLoader vl = new VacancyLoader(this.connection, this.vacancies);
        LOG.info("thread loader to queue start");
        vl.start();

        //connecting and parsing from mainThread, downloading all vacancies to DB. First start!
        this.doc = Jsoup.connect(config.get("urlPage")).get();
        int numberOfpages = getNumberOfPages();
        for (int i = 1; i <= numberOfpages; i++) {
            //flag for stopping this cycle if date of vacancy will be old
            boolean flagStop = true;
            String urlPage = String.format("%s%d", config.get("urlPage"), i);
            Document page = Jsoup.connect(urlPage).get();
            Elements mainTable = page.select("table[class=forumTable]");
            Elements rows = mainTable.select("tr");
            //skip the first four empty lines
            ListIterator<Element> iterator = rows.listIterator(4);
            while (iterator.hasNext()) {
                //columns with data
                Elements tds = iterator.next().select("td");
                //checking date if date is wrong then out from "while" cycle
                if (tds.get(5).text().matches("\\d+\\s[А-Я, а-я]+\\s\\d+,\\s\\d+:\\d+")
                        && !tds.get(5).text().split(" ")[2].equals("19,")) {
                    flagStop = false;
                    break;
                }
                //searching for java position
                if (searchJava(tds.get(1).text())) {
                    Element hr = tds.get(1).child(0);
                    loadToQueue(hr);
                };
            }
            //out from "for" cycle
            if (!flagStop) {
                break;
            }
        }
        //making "poison pill"
        vacancies.add(new Vacancy("stop", null, null));
        orderFlag = false;
        LOG.info("end of first parsing");
    }

    public void secondParse() {
        LOG.info("Second parsing");
        Elements mainTable = this.doc.select("table[class=forumTable]");
        Elements rows = mainTable.select("tr");
        ListIterator<Element> iterator = rows.listIterator(4);
        while (iterator.hasNext()) {
            Elements columns = iterator.next().select("td");
            if (columns.get(5).text().contains("вчера")) {
                System.out.println("!!!!!!!!");
                System.out.println(columns.get(1).text());
            } else {
                System.out.println("out");
                break;
            }
        }
    }

    /**
     * parse, create and load vacancy to queue
     * @param element
     * @throws IOException
     */
    public void loadToQueue(Element element) throws IOException {
        String name = element.text();
        String url = element.attr("href");
        Document job = Jsoup.connect(url).get();
        Element jobTable = job.select("table[class=msgTable]").get(0);
        Elements trJobTable = jobTable.select("tr");
        Elements tdJobTable = trJobTable.get(1).select("td");
        String description = tdJobTable.get(1).text();
        vacancies.add(new Vacancy(name, url, description));
    }

    /**
     * method for searching number of pages
     * @return
     */
    public int getNumberOfPages() {
        Elements elements = this.doc.select("table.sort_options");
        Element table = elements.get(1);
        Elements columns = table.select("td");
        Element column = columns.get(0);
        String str = column.text();
        String[] arr = str.split(" ");
        int numberOfpages = Integer.valueOf(arr[arr.length - 1]);
        return numberOfpages;
    }

    /**
     *
     * @param str
     * @return
     */
    public boolean searchJava(String str) {
        str = str.toLowerCase();
        return str.contains("java") && !str.contains("script");
    }

    @Override
    public void close() {
        try {
            LOG.info("close our parser");
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }
}

