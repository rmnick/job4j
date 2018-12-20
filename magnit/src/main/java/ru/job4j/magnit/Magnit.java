package ru.job4j.magnit;

import java.sql.*;

public class Magnit {

    public Config config;

    public Magnit(Config config) {
        this.config = config;
    }


    public static void main(String[] args) {
        Config config = new Config();
        config.init();
        Magnit magnit = new Magnit(config);
        Connection connection  = magnit.createNewDatabase("url");
        magnit.generate(10, connection);


    }

    public Connection createNewDatabase(String fileName) {

        String url = config.get(fileName);
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    public void generate(int i, Connection connection) {
        String create = "create table if not exists entries(field integer);";
        String delete = "Delete from entries;";
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(create);
            System.out.println("create table");
            st.executeUpdate(delete);
            System.out.println("delete all entries from table");
            for (int n = 0; n < i; n++) {
                st.executeUpdate(String.format("insert into entries (field) values(%d)", n));
                System.out.println(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
         }
    }
}
