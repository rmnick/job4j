package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserController.class.getName());
    private final ValidateService vs = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html");
        try {
            StringBuilder sb = new StringBuilder("<table>");
            vs.show().stream().forEach(usr -> {
                sb.append("<tr><td>" + usr.toString() + "</td>" + "<td>" + createFormUpdate(usr, req) + "</td>" + "<td>" + createFormDelete(usr, req) + "</td></tr>");
            });
            sb.append("</table>");
            PrintWriter wr = res.getWriter();
            wr.println("<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head>"
                    + "    <meta charset=\"UTF-8\">"
                    + "    <title>users</title>"
                    + "</head>"
                    + "<body>"
                    + sb.toString()
                    + "</body>"
                    + "</html>");
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        User user = vs.createUser(req.getParameter("id"), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        try {
            res.getWriter().println(vs.delete(user));
        } catch (ValidateException e) {
            res.getWriter().println(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    private String createFormUpdate(User user, HttpServletRequest request) {
        return new StringBuilder("<form method='get' action='")
                .append(request.getContextPath())
                .append("/update'>")
                .append("<input type='hidden' name='id' value='")
                .append(user.getId())
                .append("'/>")
                .append("<input type='hidden' name='name' value='")
                .append(user.getName())
                .append("'/>")
                .append("<input type='hidden' name='login' value='")
                .append(user.getLogin())
                .append("'/>")
                .append("<input type='hidden' name='email' value='")
                .append(user.getEmail())
                .append("'/>")
                .append("<button type='submit'>update</button>")
                .append("</form>").toString();
    }

    private String createFormDelete(User user, HttpServletRequest request) {
        return new StringBuilder("<form method='post' action='")
                .append(request.getContextPath())
                .append("/user'>")
                .append("<input type='hidden' name='id' value='")
                .append(user.getId())
                .append("'/>")
                .append("<button type='submit'>delete</button>")
                .append("</form>").toString();
    }
}
