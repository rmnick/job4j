package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.logic.ValidateException;
import ru.job4j.servlets.users.logic.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdate extends HttpServlet {
    private final ValidateService vs = ValidateService.getInstance();
    private static final Logger LOG = LogManager.getLogger(UserUpdate.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().println("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>users</title>"
                + "</head>"
                + "<body>"
                + "<form method='post' action='"
                + req.getContextPath()
                + "/update'>"
                + "<fieldset>"
                + "<legend>edit</legend>"
                + "<p>id : <input type='text' name='id' value='" + req.getParameter("id") + "' readonly/></p>"
                + "<p>name : <input type='text' name='name' value='" + req.getParameter("name") + "'/></p>"
                + "<p>login : <input type='text' name='login' value='" + req.getParameter("login") + "'/></p>"
                + "<p>email : <input type='text' name='email' value='" + req.getParameter("email") + "'/></p>"
                + "</fieldset>"
                + "<p><input type=\"submit\" value=\"update\"></p>"
                + "</form>"
                + "</body>"
                + "</html>");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User user = vs.createUser(req.getParameter("id"), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        try {
        res.getWriter().println(vs.update(user));
        } catch (ValidateException e) {
            res.getWriter().println(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
