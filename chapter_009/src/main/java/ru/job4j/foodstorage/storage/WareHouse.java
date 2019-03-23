package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

public class WareHouse implements IStorage {
    @Override
    public String toString() {
        return "warehouse";
    }

    @Override
    public boolean check(final Food food) {
        return false;
    }
}
