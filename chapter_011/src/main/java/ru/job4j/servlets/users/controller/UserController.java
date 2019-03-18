package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class UserController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserController.class.getName());
//    private final Dispatcher dp = Dispatcher.getInstance();
    private final ValidateService vs = ValidateService.getInstance();
//    private final Map<String, Function<User, String>> operations = new HashMap<>();

//    /**
//     * constructor only for filling internal function dispatcher
//     */
//    public UserController() {
//        fill();
//    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html");
        try {
            String formUpdate = new StringBuilder("<form action='/chapter_011")
//                    .append(req.getContextPath())
                    .append("/update' method='get'>")
                    .append("<p><input type='button' value='update'></p>")
                    .append("</form>").toString();

            StringBuilder sb = new StringBuilder("<table>");
            vs.show().stream().forEach(usr -> {
                sb.append("<tr><td>" + usr.toString() + formUpdate + "</td></tr>");
            });
            sb.append("</table>");
            PrintWriter wr = res.getWriter();
            wr.println("<!DOCTYPE html>" +
                    "<html lang=\"en\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">" +
                    "    <title>users</title>" +
                    "</head>" +
                    "<body>" +
                    sb.toString() +
                    "</body>" +
                    "</html>");
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        User user = createUser(req.getParameter("id"), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        res.getWriter().println(vs.add(user));

    }

    private User createUser(final String id, final String name, final String login, final String email) {
        User user = new User(name, login, email);
        if (id != null) {
            user.setId(id);
        }
        return user;
    }

}
