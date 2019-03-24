package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

import java.time.ZoneId;

public class WareHouse extends AbstractStorage {

    public WareHouse(String name) {
        super(name);
    }

    @Override
    public boolean check(final Food food) {
        boolean result = false;
        long expireDate = food.getExpireDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long createDate = food.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long now = System.currentTimeMillis();
        if (expireDate - now > 0) {
            double percent = ((expireDate - now) * 100 / (expireDate - createDate));
            if (percent > 75) {
                result = true;
            }
        }
        return result;
    }
}
