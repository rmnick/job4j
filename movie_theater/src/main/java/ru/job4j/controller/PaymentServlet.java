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
    private static final String TIME_IS_UP = "sorry your time's up";
    private static final String FAIL = "something went wrong, please try again";
    private static final String SUCCESS = "thank you for your purchase";

    /**
     * send information about chosen seat
     * @param req HttpRequest
     * @param resp HttpResponse
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Seat seat = service.createSeat(0, 0, 0);
            HttpSession session = req.getSession(false);
            if (session.getAttribute("id") != null) {
                String id = req.getSession(false).getAttribute("id").toString();
                System.out.println("session id from servlet payment " + id);
                seat = service.getSeat(service.createSeat(Integer.valueOf(id), 0, 0));
            }
            resp.setContentType("text/json");
            PrintWriter pw = resp.getWriter();
            mapper.writeValue(pw, seat);
            LOG.info("send json seat");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * buy ticket (chosen seat)
     * send to browser result operation
     * @param req HttpRequest
     * @param resp HttpResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(false);
            ObjectMapper mapper = new ObjectMapper();
            BufferedReader reader = req.getReader();
            PrintWriter writer = resp.getWriter();
            String json = reader.readLine();
            if (req.getSession(false) != null && req.getSession(false).getAttribute("id") != null) {
                String seatId = req.getSession(false).getAttribute("id").toString();
                Account account = mapper.readValue(json, Account.class);
                account = service.buy(account, service.createSeat(Integer.valueOf(seatId), 0, 0));
                if (account == null) {
                    writer.append(FAIL);
                } else {
                    writer.append(SUCCESS);
                }
            } else {
                writer.append(TIME_IS_UP);
            }
            reader.close();
            writer.flush();
            if (session != null) {
                session.invalidate();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
