package ru.job4j.servlets.users.storage;
/**
 * class for testing servlets
 */

import ru.job4j.servlets.users.logic.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreStub implements IStore<User> {
    private final Map<String, User> users = new HashMap<>();
    private int id = 0;

    @Override
    public User add(User user) {
        user.setId(String.valueOf(id++));
        return users.put(user.getId(), user);
    }

    @Override
    public User delete(User user) {
        return users.remove(user.getId());
    }

    @Override
    public User update(User user) {
        return users.put(user.getId(), user);
    }

    @Override
    public User getUser(User user) {
        return users.get(user.getId());
    }

    @Override
    public boolean compareLogin(User user) {
        return false;
    }

    @Override
    public boolean compareEmail(User user) {
        return false;
    }

    @Override
    public boolean authenticate(User user) {
        return true;
    }

    @Override
    public List<User> getAll() {
        return users.values().stream().collect(Collectors.toList());
    }

    @Override
    public User getUserByLogin(User user) {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
