package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.service.Account;
import ru.job4j.service.IService;
import ru.job4j.service.Seat;
import ru.job4j.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentServlet extends HttpServlet {
    public static final Logger LOG = LogManager.getLogger(PaymentServlet.class.getName());
    private final IService<Seat, Account> service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Seat seat = service.createSeat(0, 0, 0);
        HttpSession session = req.getSession(false);
        if (session.getAttribute("id") != null) {
            String id = req.getSession(false).getAttribute("id").toString();
            seat = service.getSeat(service.createSeat(Integer.valueOf(id), 0, 0));
        }
        resp.setContentType("text/json");
        PrintWriter pw = resp.getWriter();
        mapper.writeValue(pw, seat);
        LOG.info("send json seat");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        PrintWriter writer = resp.getWriter();
        Account account = new ObjectMapper().readValue(reader.readLine(), Account.class);
//        writer.append(service.buy(accounts).toString());
        reader.close();
        System.out.println(account);
//        writer.flush();
        req.getSession().invalidate();
    }
}
