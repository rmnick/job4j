package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    protected List<Food> storage = new ArrayList<>();

    public List<Food> getStorage() {
        return storage;
    }
}
