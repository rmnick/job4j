package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.logic.ValidateException;
import ru.job4j.servlets.users.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCreate extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserCreate.class.getName());
    private final ValidateService vs = ValidateService.getInstance();


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, res);
        } catch (ServletException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        User user = vs.createUser(req.getParameter("id"), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        try {
            user = vs.add(user);
            LOG.info(String.format("add user: %s", user.toString()));
            res.sendRedirect(String.format("%s/", req.getContextPath()));
        } catch (ValidateException e) {
            try {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("/WEB-INF/views/fault.jsp").forward(req, res);
            } catch (ServletException se) {
                LOG.error(se.getMessage());
            } catch (IOException ioe) {
                LOG.error(ioe.getMessage());
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
