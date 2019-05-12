package ru.job4j.servlets.users.logic;

import java.util.List;

/**
 * for testing application
 */
public interface Validate extends AutoCloseable {
    User createUser(String id, String name, String login, String password, String email, String country, String city);
    User createUser(String name, String password);
    User getUser(User user);
    User add(User user);
    User update(User user);
    User delete(User user);
    List<User> show();
    boolean authenticate(User user);
    List<String> getAllCountries();
    List<String> getAllCities(String str);
}
