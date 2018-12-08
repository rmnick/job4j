package ru.job4j.examples;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.*;

public class Query {
    private static final Logger LOG = LogManager.getLogger(Query.class.getName());
    public static void main(String[] args) {
        /*
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        */
        String url = "jdbc:postgresql://localhost:5432/test?user=postgres&password=123";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement st = conn.prepareStatement("SELECT CT p.id, p.name, p.number FROM products p left outer join types t on p.id_type = t.id where t.name = (?);");
            st.setString(1, "milk");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%d %s %d", rs.getInt("id"), rs.getString("name"), rs.getInt("number")));
            }
            rs.close();
            /*
            Statement st = conn.createStatement();
           boolean result = st.execute("create table manager (id serial primary key, name varchar(200), position varchar(200), date_start timestamp);");
           System.out.println(result);
            PreparedStatement st = conn.prepareStatement("insert into manager (name, position, date_start) values (?, ?, ?);");
            st.setString(1, "Nick");
           st.setString(2, "boss");
           st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.execute();
            System.out.println("is done");
            Statement st = conn.createStatement();
            st.execute("drop table manager;");
            System.out.println("is done");
            */
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
               try {
                   conn.close();
               } catch (SQLException e) {
                   LOG.error(e.getMessage(), e);
               }
            }
        }
    }
}
