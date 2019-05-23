package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.service.Account;
import ru.job4j.service.IService;
import ru.job4j.service.Seat;
import ru.job4j.service.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(CheckServlet.class.getName());
    private final IService<Seat, Account> service = Service.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("in check");
        try {
            System.out.println(req.getParameter("name"));
            System.out.println("phone");
            PrintWriter pw = resp.getWriter();
            pw.write("success");
            pw.flush();


        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
