package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

import java.time.ZoneId;

public class Shop extends AbstractStorage {

    public Shop(String name) {
        super(name);
    }

        @Override
        public boolean check(Food item) {
            boolean result = false;
            long expireDate = item.getExpireDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long createDate = item.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long now = System.currentTimeMillis();
            if (expireDate - now > 0) {
                double percent = ((expireDate - now) * 100 / (expireDate - createDate));
                if (percent > 25 && percent < 75) {
                    result = true;
                } else if (percent < 25) {
                    item.setDiscount(50);
                    result = true;
                }
            }
        return result;
    }

    @Override
    public String toString() {
        return "shop";
    }
}
