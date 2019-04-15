package ru.job4j.foodstorage.storage;


import ru.job4j.foodstorage.food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements IStorage {
    public final List<Food> storage = new ArrayList<>();
    protected final String name;


    public AbstractStorage(final String name) {
        this.name = name;
    }

    public void add(Food food) {
        this.storage.add(food);
    }

    public void show() {
        StringBuilder sb = new StringBuilder().append(String.format("%s:%s", name, System.lineSeparator()));
        this.storage.forEach(st -> {
            sb.append(String.format("%s ", st));
        });
        System.out.println(sb.toString());
    }

    public List<Food> getStorage() {
        return this.storage;
    }
}
