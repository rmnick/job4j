package ru.job4j.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Query {
    private final static Logger LOG = LoggerFactory.getLogger(Query.class);
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/products?user=postgres&password=password";
        //String username = "postgres";
        //String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url/*, username, password*/);
            //PreparedStatement st = conn.prepareStatement("SELECT p.id, p.name, p.number FROM products p left outer join types t on p.id_type = t.id where t.name = (?);");
            //st.setString(1, "milk");
            //ResultSet rs = st.executeQuery();
            //while (rs.next()) {
            //    System.out.println(String.format("%d %s %d", rs.getInt("id"), rs.getString("name"), rs.getInt("number")));
            //}
            //rs.close();
            //Statement st = conn.createStatement();
           // boolean result = st.execute("create table manager (id serial primary key, name varchar(200), position varchar(200), date_start timestamp);");
           // System.out.println(result);
            //PreparedStatement st = conn.prepareStatement("insert into manager (name, position, date_start) values (?, ?, ?);");
            //st.setString(1, "Nick");
           //st.setString(2, "boss");
           //st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            //st.execute();
            //System.out.println("is done");
            Statement st = conn.createStatement();
            st.execute("drop table manager;");
            System.out.println("is done");
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
