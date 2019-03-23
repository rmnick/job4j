package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

public class Shop implements IStorage {

    @Override
    public boolean check(final Food food) {
        return false;
    }
    @Override
    public String toString() {
        return "shop";
    }
}
