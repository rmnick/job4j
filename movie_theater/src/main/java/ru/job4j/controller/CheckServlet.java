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
import java.io.IOException;
import java.io.PrintWriter;

/**
 * verify if account phone number is already exist
 */
public class CheckServlet extends HttpServlet {
    public static final String SUCCESS = "success";
    public static final String FAIL = "";
    private static final Logger LOG = LogManager.getLogger(CheckServlet.class.getName());
    private final IService<Seat, Account> service = Service.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String result = FAIL;
        try {
            Account account = new Account(req.getParameter("name"), req.getParameter("phone"), "0");
            account = service.getAccount(account);
            if (account == null || account.getName() == req.getParameter("name")) {
                result = SUCCESS;
            }
            PrintWriter pw = resp.getWriter();
            pw.write(result);
            pw.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
