package ru.job4j.foodstorage.storage;


import ru.job4j.foodstorage.food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements IStorage {
    public final List<Food> storage = new ArrayList<>();

    public void add(Food food) {
        this.storage.add(food);
    }

    public void show() {
        this.storage.forEach(System.out::println);
    }
}
