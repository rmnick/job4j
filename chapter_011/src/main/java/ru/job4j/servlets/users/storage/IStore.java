package ru.job4j.servlets.users.storage;

import java.util.List;

public interface IStore<T> extends AutoCloseable {
    T add(T t);
    T delete(T t);
    T update(T t);
    T getUser(T t);
    boolean compareLogin(T t);
    boolean compareEmail(T t);
    boolean authenticate(T t);
    List<T> getAll();
    T getUserByLogin(T t);
}
