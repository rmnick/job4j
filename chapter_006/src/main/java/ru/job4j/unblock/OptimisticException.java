package ru.job4j.unblock;

public class OptimisticException extends RuntimeException {

    public OptimisticException(String name) {
        super(name);
    }
}
