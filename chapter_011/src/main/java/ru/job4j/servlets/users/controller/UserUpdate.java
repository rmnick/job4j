package ru.job4j.servlets.users.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdate extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        String id = req.getParameter("id");
        res.getWriter().println(req.getParameter("id"));
//        res.getWriter().println("<!DOCTYPE html>" +
//                "<html lang=\"en\">" +
//                "<head>" +
//                "    <meta charset=\"UTF-8\">" +
//                "    <title>users</title>" +
//                "</head>" +
//                "<body>" +
//                "<form>" +
//                        "  <fieldset>" +
//                        "    <legend>edit</legend>" +
//                        "    <p><label for=\"name\">name</label><input type=\"text\" value=\"" + id + "\"" + "id=\"name\"></p>" +
//                        "    <p><label for=\"email\">e-mail</label><input type=\"text\" id=\"email\"></p>" +
//                        "    <p><label for=\"login\">login</label><input type=\"text\" id=\"login\"></p>" +
//                        "  </fieldset>" +
//                        "<p><input type=\"submit\" value=\"update\"></p>" +
//                        "</form>" +
//                "</body>" +
//                "</html>");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {

    }
}
