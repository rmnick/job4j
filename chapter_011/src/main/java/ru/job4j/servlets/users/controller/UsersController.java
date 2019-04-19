package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersController.class.getName());
    private final Validate vs = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            List<User> list = vs.show();
            req.setAttribute("users", list);
            req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, res);
        } catch (ServletException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html");
        User user = vs.createUser(req.getParameter("id"), req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"));
        try {
            vs.delete(user);
            res.sendRedirect(String.format("%s/users", req.getContextPath()));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        try {
            vs.close();
        } catch (Exception e) {
           LOG.error(e.getMessage(), e);
        }
    }
}
