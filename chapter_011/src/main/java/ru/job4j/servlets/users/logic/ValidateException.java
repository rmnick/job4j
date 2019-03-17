package ru.job4j.servlets.users.logic;

public class ValidateException extends RuntimeException {
    public ValidateException(String msg) {
        super(msg);
    }
}
