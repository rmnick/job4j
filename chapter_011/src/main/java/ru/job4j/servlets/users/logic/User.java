package ru.job4j.servlets.users.logic;

import java.time.LocalDateTime;

public class User {
    private String id;
    private String name;
    private String email;
    private String login;
    private LocalDateTime date;

    public User(final String name, final String login, final String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("id: %s, name: %s, login: %s, email: %s, date: %s", id, name, login, email, date);
    }
}
