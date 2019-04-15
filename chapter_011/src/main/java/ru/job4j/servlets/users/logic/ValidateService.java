package ru.job4j.servlets.users.logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.storage.DbStore;

import java.util.List;

public class ValidateService implements Validate {
//    private final static Validate INSTANCE = new ValidateService();
    private final DbStore ds = DbStore.getInstance();
    private static final Logger LOG = LogManager.getLogger(ValidateService.class.getName());

    private ValidateService() {
    }

    private static class ValidateServiceHolder {
        public final static Validate INSTANCE = new ValidateService();
    }

    public static Validate getInstance() {
        return ValidateServiceHolder.INSTANCE;
    }

    /**
     * using exception for conversation between controller and logic
     * @param user User
     * @return User
     * @throws ValidateException RuntimeException
     */
    public User add(User user) throws ValidateException {
        checkName(user);
        checkLogin(user);
        checkEmail(user);
        return ds.add(user);
    }

    /**
     * must enter the correct id
     * @param user User
     * @return User
     */
    public User delete(User user) {
        return ds.delete(user);
    }

    /**
     * to update you need to fill in all the fields
     * if the field is equivalent to the previous value, validation is not required
     * @param user User
     * @return User
     * @throws ValidateException RuntimeException
     */
    public User update(User user) throws ValidateException {
        checkName(user);
        if (!user.getLogin().equals(ds.getUser(user).getLogin())) {
            checkLogin(user);
        }
        if (!user.getEmail().equals(ds.getUser(user).getEmail())) {
            checkEmail(user);
        }
        return ds.update(user);
    }

    /**
     * redirect to DbStore and check current user in there
     * @param user User
     * @return boolean
     */
    public boolean authenticate(User user) {
        return ds.authenticate(user);
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
     * check for empty and valid state
     * @param user User
     * @throws ValidateException RuntimeException
     */
    private void checkName(User user) throws  ValidateException {
        validateName(user.getName());
    }

    /**
     * check for empty, valid and existing state
     * use method compare from memoryStore
     * @param user User
     * @throws ValidateException RuntimeException
     */
    private void checkLogin(User user) throws ValidateException {
        validateLogin(user.getLogin());
        if (ds.compareLogin(user)) {
            throw new ValidateException("login is already exist");
        }
    }

    /**
     * check for empty, valid and existing state
     * use method compare from memoryStore
     * @param user User
     * @throws ValidateException RuntimeException
     */
    private void checkEmail(User user) throws ValidateException {
        validateEmail(user.getEmail());
        if (ds.compareEmail(user)) {
            throw new ValidateException("email is already exist");
        }
    }

    public List<User> show() {
        return ds.getAll();
    }

    public User getUser(User user) {
        return ds.getUserByLogin(user);
    }

    /**
     * create new User for conversation between layouts
     * @param id String
     * @param name String
     * @param login String
     * @param email String
     * @return user User
     */
    public User createUser(final String id, final String name, final String login, final String password, final String email) {
        User user = new User(name, login, password, email);
        if (id != null) {
            user.setId(id);
        }
        return user;
    }

    /**
     * for sign in servlet
     * @param login
     * @param password
     * @return User
     */
    public User createUser(final String login, final String password) {
        User user = new User(login, password);
        return user;
    }

    @Override
    public void close() {
        ds.close();
    }
}
