package ru.job4j.servlets.users.logic;

public class LoginException extends RuntimeException {
    public LoginException(String msg) {
        super(msg);
    }
}
