package ru.job4j.persistence;

import java.util.List;

public interface IHall<T, V> {
    List<T> getAll();
    T reserve(T item);
    T cancelReservation(T item);
    T getSeat(T item);
    V getAccount(V item);
    V createBuy(V item);
    V bindBuy(V item);
}
