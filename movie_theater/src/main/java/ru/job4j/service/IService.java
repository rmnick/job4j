package ru.job4j.service;

import java.util.List;
import java.util.Map;

public interface IService<T> {
    Map<Integer, List<T>> getAll();
    T createSeat(int firstArg, int secondArg);
    T reserve(T item);
}
