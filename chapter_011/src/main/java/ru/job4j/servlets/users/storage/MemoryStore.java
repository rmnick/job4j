package ru.job4j.servlets.users.storage;

import ru.job4j.servlets.users.logic.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {
    private static MemoryStore instance = new MemoryStore();
    private final Map<Integer, User> store = new ConcurrentHashMap<>();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return instance;
    }

    @Override
    public User add(User user) {
        return store.put(user.getId(), user);
    }

    @Override
    public User delete(User user) {
        return store.remove(user.getId());
    }

    @Override
    public User update(User user) {
        User temp = store.get(user.getId());
        temp.setName(user.getName());
        temp.setEmail(user.getEmail());
        return temp;
    }

    public User get(User user) {
        return store.get(user.getId());
    }
}
