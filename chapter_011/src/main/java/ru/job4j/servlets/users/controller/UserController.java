package ru.job4j.servlets.users.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class UserController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserController.class.getName());
    private final ValidateService vs = ValidateService.getInstance();
    private final Map<String, Function<User, String>> operations = new HashMap<>();

    /**
     * constructor only for filling internal function dispatcher
     */
    public UserController() {
        fill();
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html");
        try {
            PrintWriter wr = res.getWriter();
            vs.show().stream().forEach(wr::println);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html");
        try {
            /**
             * information from user
             */
            String action = req.getParameter("action");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String login = req.getParameter("login");
            String id = req.getParameter("id");
            /**
             * create dummy(user) and make response
             */
            User user = createUser(id, name, login, email);
            res.getWriter().println(getOperation(action, user));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * add function
     * starting if user input "action=add"
     * @return Function
     */
    private Function<User, String> add() {
        return usr -> {
            String result;
            try {
                result = vs.add(usr).toString();
            } catch (NameException name) {
                result = name.getMessage();
            } catch (MailException mail) {
                result = mail.getMessage();
            } catch (LoginException login) {
                result = login.getMessage();
            }
            return result;
        };
    }

    /**
     * delete function
     * starting if user input "action=delete"
     * @return Function
     */
    private Function<User, String> delete() {
        return usr -> {
            String result;
            try {
                result = vs.delete(usr).toString();
            } catch (IdException id) {
                result = id.getMessage();
            }
            return result;
        };
    }

    /**
     * update function
     * starting if user input "action=update"
     * @return Function
     */
    private Function<User, String> update() {
        return usr -> {
            String result;
            try {
                result = vs.update(usr).toString();
            } catch (IdException id) {
                result = id.getMessage();
            }
            return result;
        };
    }

    /**
     * dispatcher pattern, search right operation in Map<String, Function> (action is a key),
     * and return String as result operation(value)
     * @param action String
     * @param user User
     * @return String
     */
    private String getOperation(String action, User user) {
        String result;
        if (operations.get(action) == null) {
            result = "input action";
        } else {
            result = operations.get(action).apply(user);
        }
        return result;
    }

    private void fill() {
        operations.put("add", add());
        operations.put("delete", delete());
        operations.put("update", update());
    }

    /**
     * make some dummy for conversation between layouts
     * @param id String
     * @param name String
     * @param login String
     * @param email String
     * @return User
     */
    private User createUser(final String id, final String name, final String login, final String email) {
        User user = new User(name, login, email);
        if (id != null) {
            user.setId(id);
        }
        return user;
    }


}
