package ru.job4j.mailservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors
            .newFixedThreadPool(Runtime.getRuntime()
            .availableProcessors());

    public void mailTo(final User user) {
        pool.submit(() -> {
                String subject = new StringBuilder()
                        .append("Notification ")
                        .append(user.getName())
                        .append(" to email ")
                        .append(user.getEmail())
                        .toString();
                String body = new StringBuilder()
                        .append("Add a new event to ")
                        .append(user.getName())
                        .toString();
                send(subject, body, "HEY!");
        });
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

}
