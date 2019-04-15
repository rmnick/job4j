package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.logic.Validate;
import ru.job4j.servlets.users.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersController.class.getName());
    private final Validate vs = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            HttpSession session = req.getSession();
            String login = null;
            if (session != null) {
                login = session.getAttribute("login").toString();
            }
            User user = vs.createUser(null, null, login, null, null);
            System.out.println(vs.getUser(user));
            req.setAttribute("user", vs.getUser(user));
            req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, res);
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
            HttpSession session = req.getSession(false);
            user = vs.delete(user);
            LOG.info(String.format("delete: %s", user.toString()));
            if (session != null) {
                session.invalidate();
            }
            LOG.info(String.format("%s is leaving session", user.toString()));
            res.sendRedirect(String.format("%s/authentication", req.getContextPath()));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
