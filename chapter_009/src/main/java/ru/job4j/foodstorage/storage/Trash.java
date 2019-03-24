package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

import java.time.ZoneId;

public class Trash extends AbstractStorage {

    public Trash(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean check(final Food food) {
        boolean result =  false;
        long expireDate = food.getExpireDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long now = System.currentTimeMillis();
        long dif = (expireDate - now);
        if (dif < 0) {
            result = true;
        }
        return result;
    }
}
