package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

public class Trash extends AbstractStorage {
    @Override
    public String toString() {
        return "trash";
    }

    @Override
    public boolean check(final Food food) {
        return false;
    }
}
