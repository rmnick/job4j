package ru.job4j.servlets.users.storage;

import ru.job4j.servlets.users.logic.User;
import ru.job4j.servlets.users.logic.ValidateException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MemoryIStore implements IStore<User> {
    private static MemoryIStore instance = new MemoryIStore();
    private final Map<String, User> store = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    private MemoryIStore() {
    }

    public static MemoryIStore getInstance() {
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
        temp.setLogin(user.getLogin());
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

    /**
     * check if email already exists
     * @param user User
     * @return boolean
     */
    public boolean compareEmail(User user) throws ValidateException {
        boolean result = false;
        for (User usr : this.getUsers()) {
            if (usr.getEmail().equals(user.getEmail())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * check if login already exists and return true
     * @param user User
     * @return boolean
     */
    public boolean compareLogin(User user) {
        boolean result = false;
        for (User usr : this.getUsers()) {
            if (usr.getLogin().equals(user.getLogin())) {
                result = true;
            }
        }
        return result;
    }
}
