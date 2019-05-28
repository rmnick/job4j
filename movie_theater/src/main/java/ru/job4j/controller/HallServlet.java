package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.service.Account;
import ru.job4j.service.IService;
import ru.job4j.service.Seat;
import ru.job4j.service.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HallServlet extends HttpServlet {
    public static final Object RESERVED = new Object();
    public static final Logger LOG = LogManager.getLogger(HallServlet.class.getName());
    private final IService<Seat, Account> service = Service.getInstance();

    /**
     * hall view, send information about all seats
     * @param req HttpRequest
     * @param resp HttpResponse
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<Integer, List<Seat>> hall = service.getAll();
//            List<Seat> seats = hall.values().stream().flatMap(x -> x.stream()).collect(Collectors.toList());
//            seats.forEach(System.out::println);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/json");
            PrintWriter writer = resp.getWriter();
            LOG.info("send json");
            mapper.writeValue(writer, hall);
            writer.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * create unique session with id, reserve seat
     * @param req HttpRequest
     * @param resp HttpResponse
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Seat seat = service.reserve(service.createSeat(Integer.valueOf(req.getParameter("id")), 0, 0));
        HttpSession session = req.getSession();
        if (seat != null) {
            session.setAttribute("id", String.valueOf(seat.getId()));
        }
    }
}
