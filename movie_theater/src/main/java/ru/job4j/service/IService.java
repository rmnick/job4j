package ru.job4j.service;

import java.util.List;
import java.util.Map;

public interface IService<T, V> {
    Map<Integer, List<T>> getAll();
    T createSeat(int id, int firstArg, int secondArg);
    T reserve(T item);
    T cancelReservation(T item);
    T getSeat(T item);
    V getAccount(V item);
    V buy(V item);
}
