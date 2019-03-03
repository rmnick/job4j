package ru.job4j.magnit;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.*;

public class Magnit implements AutoCloseable {

    private Config config;
    private Connection connection;

    public Magnit(final Config config) {
        this.config = config;
        try {
            connection = DriverManager.getConnection(config.get("url"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //windows path
         File source = new File("C:\\projects\\job4j\\magnit\\src\\main\\resources\\source.xml");
         File result = new File("C:\\projects\\job4j\\magnit\\src\\main\\resources\\result.xml");
         /*Ubuntu path
         File source = new File("/home/nick/work/job4j/magnit/src/main/resources/source.xml");
         File result = new File("/home/nick/work/job4j/magnit/src/main/resources/result.xml");
         */
        int n = 1000000;
        Config config = new Config();
        Magnit magnit = new Magnit(config);
        XmlUsage xml = new XmlUsage();
        Stylizer style = new Stylizer();
        long timeStart = System.currentTimeMillis();
        magnit.init();
        magnit.generate(n);
        xml.createXML(magnit.getConnection(), source);
        style.changeStyle(source, result);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(result, new MagnitHandler());
        long timeFinish = System.currentTimeMillis();
        System.out.println(String.format("time calculation is %d \n", timeFinish - timeStart));
    }

    public void init() {
        String create = "create table if not exists entries(field integer);";
        String delete = "Delete from entries;";
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(create);
            System.out.println("create table");
            st.executeUpdate(delete);
            System.out.println("delete all entries from table");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generate(int i) {
        System.out.println("insert entries into table");
        String insert = "insert into entries (field) values (?)";
        try (PreparedStatement ps = connection.prepareStatement(insert)) {
            connection.setAutoCommit(false);
            for (int n = 1; n <= i; n++) {
                ps.setInt(1, n);
                ps.addBatch();
                //System.out.println(n);
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rb) {
                    rb.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}