package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.servlets.users.logic.Validate;
import ru.job4j.servlets.users.logic.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LocationController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(LocationController.class.getName());
    private final Validate vs = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> countries = vs.getAllCountries();
            String listCountries = mapper.writeValueAsString(countries);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/json");
            PrintWriter writer = resp.getWriter();
            writer.print(listCountries);
            writer.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String country = req.getParameter("country");
            List<String> cities = vs.getAllCities(country);
            String listCities = mapper.writeValueAsString(cities);
            resp.setContentType("text/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print(listCities);
            writer.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
