package ru.job4j.servlets.json;

import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public class JsonServlet extends HttpServlet {
    public final JsonStore store = JsonStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        StringWriter str = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(str, store.getAll());
        String result = str.toString();
        pw.append(result);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//        String lastname = req.getParameter("lastName");
//        JsonUser user = new JsonUser(id, name, lastname);
        BufferedReader br = req.getReader();
        String line = null;
        StringBuilder sb  = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        ObjectMapper om = new ObjectMapper();
        JsonUser user = om.readValue(sb.toString(), JsonUser.class);
        store.add(user);
    }
}
