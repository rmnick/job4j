package ru.job4j.servlets.users.logic;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String email;
    private LocalDateTime date;

    public User(final String name, final String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("id: %s, name: %s email: %s, date: %s", id, name, email, date);
    }
}
