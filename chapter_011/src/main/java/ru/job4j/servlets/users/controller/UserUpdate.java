package ru.job4j.servlets.users.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdate extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().println("hello from update");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {

    }
}
