package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

public class WareHouseFixed extends AbstractDecoratorStorage {
    public WareHouseFixed(String name, AbstractStorage storage) {
        super(name, storage);
    }

    @Override
    public boolean check(Food item) {
        return (store.check(item) && isFull());
    }

    private boolean isFull() {
        return this.storage.size() < 2;
    }
}
