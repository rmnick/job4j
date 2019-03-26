package ru.job4j.servlets.users.logic;

import ru.job4j.servlets.users.storage.DbStore;
import ru.job4j.servlets.users.storage.MemoryIStore;

import java.util.List;

public class ValidateService {
    private static ValidateService instance = new ValidateService();
    private final DbStore ds = DbStore.getInstance();
    private final MemoryIStore ms = MemoryIStore.getInstance();

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
        User result = user;
        checkName(user);
        checkLogin(user);
        checkEmail(user);
        result = user;
        ms.add(result);
        ds.add(result);
        return result;
    }

    /**
     * must enter the correct id
     * @param user User
     * @return User
     */
    public User delete(User user) {
        ds.delete(user);
        User result = ms.delete(user);
        return result;
    }

    /**
     * to update you need to fill in all the fields
     * @param user User
     * @return User
     * @throws ValidateException RuntimeException
     */
    public User update(User user) throws ValidateException {
        checkName(user);
        if (!user.getLogin().equals(ms.getUser(user).getLogin())) {
            checkLogin(user);
        }
        if (!user.getEmail().equals(ms.getUser(user).getEmail())) {
            checkEmail(user);
        }
        ds.update(user);
        return ms.update(user);
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
        if (ms.compareLogin(user)) {
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
        if (ms.compareEmail(user)) {
            throw new ValidateException("email is already exist");
        }
    }

    public List<User> show() {
        return ms.getUsers();
    }

    /**
     * create new User for conversation between layouts
     * @param id String
     * @param name String
     * @param login String
     * @param email String
     * @return user User
     */
    public User createUser(final String id, final String name, final String login, final String email) {
        User user = new User(name, login, email);
        if (id != null) {
            user.setId(id);
        }
        return user;
    }
}
