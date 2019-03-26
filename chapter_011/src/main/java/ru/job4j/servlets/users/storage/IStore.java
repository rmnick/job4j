package ru.job4j.servlets.users.storage;

public interface IStore<T> {
    T add(T t);
    T delete(T t);
    T update(T t);
}
