package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.logic.Validate;
import ru.job4j.servlets.users.logic.ValidateException;
import ru.job4j.servlets.users.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserCreate extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserCreate.class.getName());
    private final Validate vs = ValidateService.getInstance();


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
        User user = vs.createUser(req.getParameter("id"), req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"), req.getParameter("country"), req.getParameter("city"));
        try {
            user = vs.add(user);
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("login") == null) {
                session = req.getSession();
                session.setAttribute("login", user.getLogin());
            }
            res.sendRedirect(String.format("%s/users", req.getContextPath()));
        } catch (ValidateException e) {
            try {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("/WEB-INF/views/fault.jsp").forward(req, res);
            } catch (ServletException se) {
                LOG.error(se.getMessage(), e);
            } catch (IOException ioe) {
                LOG.error(ioe.getMessage(), e);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
