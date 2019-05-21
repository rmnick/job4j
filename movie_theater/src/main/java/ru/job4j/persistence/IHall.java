package ru.job4j.persistence;

import java.util.List;

public interface IHall<T, V> {
    List<T> getAll();
    T reserve(T item);
    T getSeat(T item);
    V buy(V item);
}
