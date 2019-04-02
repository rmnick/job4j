package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

public class TrashReproductor extends AbstractDecoratorStorage {

    public TrashReproductor(String name, AbstractStorage storage) {
        super(name, storage);
    }

    @Override
    public boolean check(Food item) {
        boolean result = false;
        if (store.check(item) && item.isCanReproduct()) {
            result = true;
        }
        return result;
    }
}
