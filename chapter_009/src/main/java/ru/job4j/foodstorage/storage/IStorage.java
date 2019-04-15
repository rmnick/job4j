package ru.job4j.foodstorage.storage;


import ru.job4j.foodstorage.food.Food;

import java.util.List;

public interface IStorage<T> {
    boolean check(Food item);
    void add(Food item);
    List<T> getStorage();
    void show();
}
