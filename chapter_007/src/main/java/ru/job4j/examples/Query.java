package ru.job4j.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/car_catalog?user=postgres&password=password&ssl=true";
        Connection conn = DriverManager.getConnection(url);
       // Statement st = conn.createStatement();
        System.out.println(conn.isValid(500));
        conn.close();

    }
}
