package ru.job4j.magnit;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.*;

public class Magnit {

    private final Config config;

    public Magnit(final Config config) {
        this.config = config;
    }

    public static void main(String[] args) throws Exception {
        File source = new File("C:\\projects\\job4j\\magnit\\src\\main\\resources\\source.xml");
        File result = new File("C:\\projects\\job4j\\magnit\\src\\main\\resources\\result.xml");
        int n = 100;
        Config config = new Config();
        Magnit magnit = new Magnit(config);
        XmlUsage xml = new XmlUsage();
        Stylizer style = new Stylizer();
        magnit.generate(n);
        long timeStart = System.currentTimeMillis();
        xml.createXML(config, source);
        style.changeStyle(source, result);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(result, new MagnitHandler());
        long timeFinish = System.currentTimeMillis();
        System.out.println(timeFinish - timeStart);
    }

    public void generate(int i) {
        String create = "create table if not exists entries(field integer);";
        String delete = "Delete from entries;";
        try (Connection connection = DriverManager.getConnection(config.get("url"));
             Statement st = connection.createStatement()) {
            st.executeUpdate(create);
            System.out.println("create table");
            st.executeUpdate(delete);
            System.out.println("delete all entries from table");
            System.out.println(String.format("insert entries into table"));
            for (int n = 0; n < i; n++) {
                st.executeUpdate(String.format("insert into entries (field) values(%d)", n));
                //System.out.println(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
