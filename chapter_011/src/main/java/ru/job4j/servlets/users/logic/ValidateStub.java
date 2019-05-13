package ru.job4j.servlets.users.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * for testing application
 */
public class ValidateStub implements Validate {
    private final Map<String, User> users = new HashMap<>();
    private int id = 0;

    @Override
    public User createUser(String id, String name, String login, String password, String email, String country, String city) {
        return new User(name, login, password, email, country, city);
    }

    @Override
    public User createUser(String login, String password) {
        User user = new User(login, password);
        return user;
    }

    @Override
    public User add(User user) {
        user.setId(String.valueOf(this.id++));
        users.put(user.getId(), user);
        return getUser(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    public User getUser(User user) {
        return users.get(user.getId());
    }

    public List<User> show() {
        return users.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean authenticate(User user) {
        return false;
    }

    @Override
    public List<String> getAllCountries() {
        return null;
    }

    @Override
    public List<String> getAllCities(String str) {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
