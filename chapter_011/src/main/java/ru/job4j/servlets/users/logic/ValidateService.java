package ru.job4j.servlets.users.logic;

import ru.job4j.servlets.users.storage.MemoryStore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ValidateService {
    private static ValidateService instance = new ValidateService();
    private final MemoryStore ms = MemoryStore.getInstance();
    private final Map<String, Function<User, String>> operations = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private ValidateService() {
        operations.put("add", add());
    }

    public static ValidateService getInstance() {
        return instance;
    }

    private Function<User, String> add() {
        return usr -> {
            usr.setDate(LocalDateTime.now());
            usr.setId(generateId());
            this.ms.add(usr);
            return this.ms.get(usr).toString();
        };
    }

    public String getOperation(String action, User user) {
        return operations.get(action).apply(user);
    }

    private int generateId() {
        return counter.incrementAndGet();
    }
}
