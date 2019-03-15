package ru.job4j.servlets.users.logic;

import ru.job4j.servlets.users.storage.MemoryStore;

import java.io.DataInputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ValidateService {
    private static ValidateService instance = new ValidateService();
    private final MemoryStore ms = MemoryStore.getInstance();
    private AtomicInteger counter = new AtomicInteger(0);

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return instance;
    }

    /**
     * using exception for conversation between controller and logic
     * @param user User
     * @return User
     * @throws NameException RuntimeException
     * @throws MailException RuntimeException
     * @throws LoginException RuntimeException
     */
    public User add(User user) throws NameException, MailException, LoginException {
        User result = null;
        if (user.getName() == null || user.getName().equals("")) {
            throw new NameException();
        } else if (user.getLogin() == null || user.getLogin().equals("")) {
            throw new LoginException();
        } else if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new MailException();
        } else {
            user.setDate(LocalDateTime.now());
            user.setId(generateId());
            result = user;
            ms.add(result);
        }
        return result;
    }

    public List<User> show() {
        return ms.show();
    }

    private String generateId() {
        return String.valueOf(counter.incrementAndGet());
    }
}
