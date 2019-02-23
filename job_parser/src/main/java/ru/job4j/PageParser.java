package ru.job4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ListIterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PageParser implements AutoCloseable {

    private Connection connection = null;
    private final Config config;

    public PageParser(final Config config) {
        this.config = config;
        try {
            this.connection = DriverManager.getConnection(config.get("url"), config.get("username"), config.get("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDb() {
            String create = "create table if not exists vacancies(id serial primary key," +
                    "name varchar(1500), url varchar (1500), description text);";
            try (Statement st = connection.createStatement()) {
                st.execute(create);
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void loadDb(Vacancy vacancy) {
        String insert = "insert into vacancies(name, url, description) values(?, ?, ?);";
        try(PreparedStatement ps = connection.prepareStatement(insert)) {
            ps.setString(1, vacancy.getName());
            ps.setString(2, vacancy.getUrl());
            ps.setString(3, vacancy.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class QuartzJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            try {
                System.out.println("start operation");
                Document doc = Jsoup.connect(config.get("urlPage")).get();
                Elements mainTable = doc.select("table[class=forumTable]");
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
                System.out.println("stop operation");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, SchedulerException {
        System.out.println("start");
        Config config = new Config();
        PageParser pageParser = new PageParser(config);
        QuarztJob qz = new QuarztJob("fuck");
        /*
        BlockingQueue<Vacancy> vacancies = new ArrayBlockingQueue<>(50);

        //thread for loading to DB from BlockingQueue. stops when takes the "poison pill"
        Thread dbLoader = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Vacancy vacancy = vacancies.take();
                        if (vacancy.getName().equals("stop")) {
                            System.out.println("stop");
                            break;
                        }
                        pageParser.loadDb(vacancy);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        dbLoader.start();

        //create table "vacancies" for saving our downloading vacancies
        pageParser.createDb();

        //connecting and parsing from mainThread, downloading all vacancies to DB. First start!
        Document doc = Jsoup.connect(config.get("urlPage")).get();
        Elements elements = doc.select("table.sort_options");
        Element table = elements.get(1);
        Elements columns = table.select("td");
        Element column = columns.get(0);
        String str = column.text();
        String[] arr = str.split(" ");
        int numberOfpages = Integer.valueOf(arr[arr.length - 1]);
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
                if (tds.get(1).text().toLowerCase().contains("java") && !tds.get(1).text().toLowerCase().contains("script")) {
                    Element hr = tds.get(1).child(0);
                    String name = hr.text();
                    String url = hr.attr("href");
                    Document job = Jsoup.connect(url).get();
                    Element jobTable = job.select("table[class=msgTable]").get(0);
                    Elements trJobTable = jobTable.select("tr");
                    Elements tdJobTable = trJobTable.get(1).select("td");
                    String description = tdJobTable.get(1).text();
                    //adding vacancy to BlockingQueue
                    vacancies.add(new Vacancy(name, url, description));
                };
            }
            //out from "for" cycle
            if (!flagStop) {
                break;
            }
        }
        //making "poison pill"
        vacancies.add(new Vacancy("stop", null, null));
*/
        //second step. start our scheduler
        JobDetail job = JobBuilder.newJob(QuarztJob.class).build();
        Trigger tr = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
        Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
        sc.start();
        sc.scheduleJob(job, tr);
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

class Vacancy {
    private final String name;
    private final String url;
    private final String description;

    public Vacancy(final String name, final String url, final String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("name : %s%surl : %s%sdescription : %s", this.name, System.lineSeparator(), this.url, System.lineSeparator(), description);
    }
        }
