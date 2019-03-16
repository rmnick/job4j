package ru.job4j.servlets.users.logic;

public class MailException extends RuntimeException {
    public MailException(String msg) {
        super(msg);
    }
}
