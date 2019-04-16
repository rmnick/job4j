package ru.job4j.servlets.users.storage;

import ru.job4j.servlets.users.logic.User;

import java.util.List;

public class StoreStub implements IStore<User> {
    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User getUser(User user) {
        return null;
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
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByLogin(User user) {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
