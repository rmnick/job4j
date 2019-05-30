package ru.job4j.persistence;

import java.util.List;

public interface IHall<T, V> extends AutoCloseable {
    List<T> getAll();
    T reserve(T item);
    T cancelReservation(T item);
    T getSeat(T item);
    V getAccount(V item);
    V createBuy(V argOne, T argTwo);
    V bindBuy(V argOne, T argTwo);
}
