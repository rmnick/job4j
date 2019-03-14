package ru.job4j.servlets.echo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EchoServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(EchoServlet.class.getName());
    private final List<String> users = new CopyOnWriteArrayList<>();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html");
        try {
            LOG.info("start response");
            res.getWriter().write("Hello world");
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        String name = req.getParameter("login");
        users.add(name);
        try {
            res.getWriter().println(users);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
