package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.logic.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserController.class.getName());
    private final ValidateService vs = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html");
        try {
            res.getWriter().println("USER");
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html");
        try {
            String action = req.getParameter("action");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            User user = new User(name, email);
            String answer = vs.getOperation(action, user);
            res.getWriter().println(answer);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }


    }

}
