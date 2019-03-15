package ru.job4j.servlets.users.storage;

import ru.job4j.servlets.users.logic.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemoryStore implements Store {
    private static MemoryStore instance = new MemoryStore();
    private final Map<String, User> store = new ConcurrentHashMap<>();

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

    public List<User> show() {
        return store.values().stream().collect(Collectors.toList());
    }

    public User get(User user) {
        return store.get(user.getId());
    }
}
