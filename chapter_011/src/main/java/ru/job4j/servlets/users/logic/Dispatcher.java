package ru.job4j.servlets.users.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Dispatcher {
    private final ValidateService vs = ValidateService.getInstance();
    private static final Dispatcher INSTANCE = new Dispatcher();
    private final Map<String, Function<User, String>> operations = new HashMap<>();

    /**
     * private constructor Dispatcher
     * fill the map with operation
     */
    private Dispatcher() {
        fill();
    }

    public static Dispatcher getInstance() {
        return INSTANCE;
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
            } catch (ValidateException e) {
                result = e.getMessage();
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
            } catch (ValidateException e) {
                result = e.getMessage();
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
            } catch (ValidateException e) {
                result = e.getMessage();
            }
            return result;
        };
    }

//    private Function<User, String> show() {
//        return usr -> {
//            StringBuilder sb = new StringBuilder("<table>");
//            vs.show().stream().forEach(user -> {
//                sb.append("<tr><td>" + user.toString() + "</td></tr>");
//            });
//            sb.append("</table>");
//            return sb.toString();
//        };
//    }

    /**
     * dispatcher pattern, search right operation in Map<String, Function> (action is a key),
     * and return String as result operation(value)
     * @param action String
     * @param user User
     * @return String
     */
    public String getOperation(String action, User user) {
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
//        operations.put("show", show());
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
