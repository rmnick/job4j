package ru.job4j.servlets.users.controller;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * check session for each user, and redirect to "sign in" page if session doesn't exist
 */
public class AuthFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(UsersController.class.getName());

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        try {
            if (request.getRequestURI().contains("/authentication")) {
                filterChain.doFilter(req, res);
            } else {
                HttpSession session = request.getSession(false);
                if (session == null) {
                    response.sendRedirect(String.format("%s/authentication", request.getContextPath()));
                } else {
                    filterChain.doFilter(req, res);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } catch (ServletException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
