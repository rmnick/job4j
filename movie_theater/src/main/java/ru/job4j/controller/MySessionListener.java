package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.service.Account;
import ru.job4j.service.IService;
import ru.job4j.service.Seat;
import ru.job4j.service.Service;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {
    public static final Logger LOG = LogManager.getLogger(MySessionListener.class.getName());
    private final IService<Seat, Account> service = Service.getInstance();

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Integer id = Integer.valueOf(se.getSession().getAttribute("id").toString());
        System.out.println("in session listener");
        System.out.println(se.getSession().getAttribute("id"));
        Seat seat = service.createSeat(id, 0, 0);
        if (service.getSeat(seat).getAccountId() == 0) {
            service.cancelReservation(seat);
        }

    }
}
