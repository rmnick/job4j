package ru.job4j.mailservice;

public class User {
    private final String name;

    private final String email;

    public User(final String name, final String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
