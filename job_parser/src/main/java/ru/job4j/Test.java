package ru.job4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.management.Descriptor;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test implements AutoCloseable {

    private Connection connection = null;

    public Test(final Config config) {
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

    public void loadDb(Job job) {
        String insert = "insert into vacancies(name, url, description) values(?, ?, ?);";
        try(PreparedStatement ps = connection.prepareStatement(insert)) {
            ps.setString(1, job.getName());
            ps.setString(2, job.getUrl());
            ps.setString(3, job.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uploadDb(List<Job> list) {
        String select = "select v.name, v.url, v.description from vacancies as v";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                String name = rs.getString(1);
                String url = rs.getString(2);
                String description = rs.getString(3);
                list.add(new Job(name, url, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Test test = new Test(new Config());
        test.createDb();

        List<Job> jobs = new ArrayList<>();
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements elements = doc.select("table.sort_options");
        Element table = elements.get(1);
        Elements columns = table.select("td");
        Element column = columns.get(0);
        String str = column.text();
        String[] arr = str.split(" ");
        int number = Integer.valueOf(arr[arr.length - 1]);
        for (int i = 1; i < 2; i++) {
            boolean flag = true;
            //System.out.println();
            //System.out.println("page " + i);
            //System.out.println();
            String urlPage = String.format("https://www.sql.ru/forum/job-offers/%d", i);
            Document page = Jsoup.connect(urlPage).get();
            Elements els = page.select("table[class=forumTable]");
            Elements rows = els.select("tr");
            ListIterator<Element> iterator = rows.listIterator(4);
            while (iterator.hasNext()) {
                Elements tds = iterator.next().select("td");
                if (tds.get(5).text().matches("\\d+\\s[А-Я, а-я]+\\s\\d+,\\s\\d+:\\d+")) {
                    if (!tds.get(5).text().split(" ")[2].equals("19,")) {
                        flag = false;
                        break;
                    }
                }
                if (tds.get(1).text().toLowerCase().contains("java") && !tds.get(1).text().toLowerCase().contains("script")) {
                    Element hr = tds.get(1).child(0);
                    String name = hr.text();
                    String url = hr.attr("href");
                    Document job = Jsoup.connect(url).get();
                    Element jobTable = job.select("table[class=msgTable]").get(0);
                    Elements trJobTable = jobTable.select("tr");
                    Elements tdJobTable = trJobTable.get(1).select("td");
                    String description = tdJobTable.get(1).text();
                    jobs.add(new Job(name, url, description));
                };
            }
            if (!flag) {
                break;
            }
        }
        System.out.println(jobs.size());
        jobs.forEach(test::loadDb);
        jobs.clear();
        System.out.println(jobs.size());
        test.uploadDb(jobs);
        System.out.println(jobs.size());
        jobs.forEach(System.out::println);



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

class Job {
    private final String name;
    private final String url;
    private final String description;

    public Job(final String name, final String url, final String description) {
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
