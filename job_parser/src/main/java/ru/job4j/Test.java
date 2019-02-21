package ru.job4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.management.Descriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) throws IOException {
        List<Job> jobs = new ArrayList<>();
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements elements = doc.select("table.sort_options");
        Element table = elements.get(1);
        Elements columns = table.select("td");
        Element column = columns.get(0);
        String str = column.text();
        String[] arr = str.split(" ");
        int number = Integer.valueOf(arr[arr.length - 1]);
        for (int i = 1; i < number; i++) {
            boolean flag = true;
            System.out.println();
            System.out.println("page " + i);
            System.out.println();
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
                    //System.out.println(hr.text());
                    //System.out.println(hr.attr("href"));
                    String name = hr.text();
                    String url = hr.attr("href");
                    Document job = Jsoup.connect(url).get();
                    Element jobTable = job.select("table[class=msgTable]").get(0);
                    Elements trJobTable = jobTable.select("tr");
                    Elements tdJobTable = trJobTable.get(1).select("td");
                    String description = tdJobTable.get(1).text();
                    jobs.add(new Job(name, url, description));
                    //System.out.println(tdJobTable.get(1).text());
                };
            }
            if (!flag) {
                break;
            }
        }
        jobs.forEach(System.out::println);
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
