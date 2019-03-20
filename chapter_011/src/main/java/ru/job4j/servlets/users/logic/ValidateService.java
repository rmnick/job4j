package ru.job4j.servlets.users.logic;

import ru.job4j.servlets.users.storage.MemoryStore;

import java.util.List;

public class ValidateService {
    private static ValidateService instance = new ValidateService();
    private final MemoryStore ms = MemoryStore.getInstance();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return instance;
    }

    /**
     * using exception for conversation between controller and logic
     * @param user User
     * @return User
     * @throws ValidateException RuntimeException
     */
    public User add(User user) throws ValidateException {
        User result;
        checkName(user);
        checkLogin(user);
        checkEmail(user);
        result = user;
        ms.add(result);
        return result;
    }

    /**
     * must enter the correct id
     * @param user User
     * @return User
     * @throws ValidateException RuntimeException
     */
    public User delete(User user) throws ValidateException {
        if (!checkNullField(user.getId())) {
            throw new ValidateException("field id is empty");
        }
        User result = ms.delete(user);
        if (result == null) {
            throw new ValidateException("input correct id");
        }
        return result;
    }

    /**
     * to update you need to fill in all the fields
     * @param user User
     * @return User
     * @throws ValidateException RuntimeException
     */
    public User update(User user) throws ValidateException {
        if (!checkNullField(user.getId())) {
            throw new ValidateException("field id is empty");
        }
        User result = ms.getUser(user);
        if (result == null) {
            throw new ValidateException("input correct id");
        }
        /**
         * update name
         */
        checkName(user);
        result.setName(user.getName());
        /**
         * update login
         */
        if (!checkNullField(user.getLogin())) {
            throw new ValidateException("field login is empty");
        }
        if (!result.getLogin().equals(user.getLogin())) {
            validateLogin(user.getLogin());
            result.setLogin(user.getLogin());
        }
        /**
         * update email
         */
        if (!checkNullField(user.getEmail())) {
           throw  new ValidateException("field email is empty");
        }
        if (!result.getEmail().equals(user.getEmail())) {
            validateEmail(user.getEmail());
            result.setEmail(user.getLogin());
        }
        return result;
    }

    /**
     * return false if user field is empty (string == null)
     * @param str String
     * @return boolean
     */
    private boolean checkNullField(String str) {
        boolean result = false;
        if (str != null) {
            result = true;
        }
        return result;
    }

    /**
     * the name cannot contain numeric characters
     * the name must start with a capital letter and contain less than 15 characters
     * @param str String
     * @throws ValidateException RuntimeException
     */
    private void validateName(String str) throws ValidateException {
        if (!str.matches("^[A-Z]{1}[a-z]{0,15}")) {
            throw  new ValidateException("input correct name");
        }
    }

    /**
     * must contain less than 15 characters(without special symbols)
     * @param str String
     * @throws ValidateException RuntimeException
     */
    private void validateLogin(String str) throws ValidateException {
        if (!str.matches("[^-\\s][a-zA-Z0-9-_\\\\s]*$")) {
            throw new ValidateException("input correct login");
        }
    }

    /**
     * example "r@n.ru"
     * @param str String
     */
    private void validateEmail(String str) {
        if (!str.matches(".+@.+\\..+")) {
            throw new ValidateException("input correct email");
        }
    }

    /**
     * check if login is already exist and throw exception
     * @param user User
     * @return boolean
     * @throws ValidateException RuntimeException
     */
    private void compareLogin(User user) throws ValidateException {
        for (User usr : ms.getUsers()) {
            if (usr.getLogin().equals(user.getLogin())) {
                throw new ValidateException("login is already exist");
            }
        }
    }

    /**
     * check if email is already exist and throw exception
     * @param user User
     * @throws ValidateException RuntimeException
     */
    private void compareEmail(User user) throws ValidateException {
        for (User usr : ms.getUsers()) {
            if (usr.getEmail().equals(user.getEmail())) {
                throw new ValidateException("email is already exist");
            }
        }
    }

    private void checkName(User user) throws  ValidateException {
        if (!checkNullField(user.getName())) {
            throw new ValidateException("name field is empty");
        }
        validateName(user.getName());
    }

    private void checkLogin(User user) throws ValidateException {
        if (!checkNullField(user.getLogin())) {
            throw new ValidateException("field login is empty");
        }
        validateLogin(user.getLogin());
        compareLogin(user);
    }

    private void checkEmail(User user) throws ValidateException {
        if (!checkNullField(user.getEmail())) {
            throw new ValidateException("field email is empty");
        }
        validateEmail(user.getEmail());
        compareEmail(user);
    }

    public List<User> show() {
        return ms.getUsers();
    }

    public User createUser(final String id, final String name, final String login, final String email) {
        User user = new User(name, login, email);
        if (id != null) {
            user.setId(id);
        }
        return user;
    }
}
