package ru.job4j.start;

import java.util.List;
import java.util.function.Predicate;

public interface ITracker {
    Item addItem(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(Predicate<String> predicate);
    Item findById(Predicate<String> predicate);
}
