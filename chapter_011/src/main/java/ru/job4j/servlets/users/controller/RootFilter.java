package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.logic.ValidateService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * redirect usual users to their page
 * root can forward to page with information about all users
 */
public class RootFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(UsersController.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("login") != null && session.getAttribute("login").equals("root")) {
                filterChain.doFilter(req, res);
            } else {
                response.sendRedirect(String.format("%s/user", request.getContextPath()));
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } catch (ServletException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
