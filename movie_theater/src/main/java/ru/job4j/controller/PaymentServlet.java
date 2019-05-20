package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.service.IService;
import ru.job4j.service.Seat;
import ru.job4j.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentServlet extends HttpServlet {
    public static final Logger LOG = LogManager.getLogger(PaymentServlet.class.getName());
    private final IService<Seat> service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String id = req.getSession(false).getAttribute("id").toString();
        Seat seat = service.getSeat(service.createSeat(Integer.valueOf(id), 0, 0));
        resp.setContentType("text/json");
        PrintWriter pw = resp.getWriter();
        mapper.writeValue(pw, seat);
        LOG.info("send json seat");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
