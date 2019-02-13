package ru.job4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements elements = doc.getElementsByAttributeValue("class", "postslisttopic");
        elements.forEach(element -> {
            Element hrElement = element.child(0);
            String url = hrElement.attr("href");
            String name = hrElement.text();
            if (name.contains(" Java ")) {
                System.out.println("!!!!!");
                try {
                    Document page = Jsoup.connect(url).get();
                    Elements elementsPage = page.getElementsByAttributeValue("class", "msgTable");
                    elementsPage.forEach(elementPage -> {
                        Element tr = elementPage.attr("class", "msgBody");
                        System.out.println(tr.text());
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}

class Job {
    private final String name;
    private final String url;

    public Job(final String name, final String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    @Override
    public String toString() {
        return String.format("name : %s ;  url : %s", this.name, this.url);
    }
        }
