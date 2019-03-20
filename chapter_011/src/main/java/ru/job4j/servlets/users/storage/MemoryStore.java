package ru.job4j.servlets.users.storage;

import ru.job4j.servlets.users.logic.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MemoryStore implements Store<User> {
    private static MemoryStore instance = new MemoryStore();
    private final Map<String, User> store = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return instance;
    }

    @Override
    public User add(User user) {
        String id = generateId();
        user.setId(id);
        user.setDate(LocalDateTime.now());
        return store.put(id, user);
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

    /**
     * using AtomicInteger for thread safety
     * @return String
     */
    private String generateId() {
        return String.valueOf(counter.incrementAndGet());
    }

    public List<User> getUsers() {
        return store.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }

    public User getUser(User user) {
        return store.get(user.getId());
    }
}
