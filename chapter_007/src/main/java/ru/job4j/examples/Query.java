package ru.job4j.examples;

import org.postgresql.core.SqlCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.sql.*;

public class Query {
    private final static Logger LOG = LoggerFactory.getLogger(Query.class);
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/test";
        String username = "postgres";
        String password = "123";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement("SELECT p.id, p.name, p.number FROM products p left outer join types t on p.id_type = t.id where t.name = (?);");
            st.setString(1, "milk");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%d %s %d", rs.getInt("id"), rs.getString("name"), rs.getInt("number")));
            }
            rs.close();
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
