package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.service.Account;
import ru.job4j.service.IService;
import ru.job4j.service.Seat;
import ru.job4j.service.Service;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HallServlet extends HttpServlet {
    public static final Object RESERVED = new Object();
    public static final Logger LOG = LogManager.getLogger(HallServlet.class.getName());
    private final IService<Seat, Account> service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, List<Seat>> hall = service.getAll();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        LOG.info("send json");
        mapper.writeValue(writer, hall);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Seat seat = service.getSeat(service.createSeat(Integer.valueOf(req.getParameter("id")), 0, 0));
        HttpSession session = req.getSession();
        synchronized (RESERVED) {
            if (!service.getSeat(seat).isBooked()) {
                session.setAttribute("id", req.getParameter("id"));
                service.reserve(seat);
            }
        }
    }
}
