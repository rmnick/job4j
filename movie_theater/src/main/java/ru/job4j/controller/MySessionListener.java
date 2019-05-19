package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.service.IService;
import ru.job4j.service.Service;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {
    public static final Logger LOG = LogManager.getLogger(MySessionListener.class.getName());
    private final IService service = Service.getInstance();

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
