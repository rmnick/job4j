package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersController.class.getName());
    private final ValidateService vs = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, res);
        } catch (ServletException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        User user = vs.createUser(req.getParameter("login"), req.getParameter("password"));
        try {
            if (vs.authenticate(user)) {
                HttpSession session = req.getSession();
                session.setAttribute("login", user.getLogin());
                res.sendRedirect(String.format("%s/users", req.getContextPath()));
            } else {
                req.setAttribute("error", "incorrect login or password");
                doGet(req, res);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

    }
}
