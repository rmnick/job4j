package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

public class WareHouseExtended extends AbstractDecoratorStorage {

    public WareHouseExtended(String name, AbstractStorage store) {
        super(name, store);
    }

    @Override
    public boolean check(Food item) {
       return store.check(item);
    }
}
