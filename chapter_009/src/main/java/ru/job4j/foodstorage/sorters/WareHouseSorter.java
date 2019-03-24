package ru.job4j.foodstorage.sorters;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.storage.IStorage;

import java.time.ZoneId;
import java.util.List;

public class WareHouseSorter extends AbstractSorter {
    public WareHouseSorter(IStorage IStorage) {
        super(IStorage);
    }

    @Override
    public void sort(List<Food> foods) {
        foods.forEach(food -> {
            long expireDate = food.getExpireDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long createDate = food.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long now = System.currentTimeMillis();
            if (expireDate - now > 0) {
                double percent = ((expireDate - now) * 100 / (expireDate - createDate));
                if (percent > 75) {
//                    this.IStorage.getStorage().add(food);
                }
            }
        });
    }

    @Override
    public String toString() {
        return String.format("wareHouseSorter dispatches to %s IStorage", this.IStorage.toString());
    }
}
