package ru.job4j.servlets.users.logic;

import java.time.LocalDateTime;

public class User {
    private String id;
    private String name;
    private String email;
    private String login;
    private String password;
    private LocalDateTime date;
    private String country;
    private String city;

    public User(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    public User(final String name, final String login, final String password, final String email, final String country, final String city) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
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

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("id: %s, name: %s, login: %s, email: %s, country: %s, city: %s, date: %s", id, name, login, email, country, city, date);
    }
}
