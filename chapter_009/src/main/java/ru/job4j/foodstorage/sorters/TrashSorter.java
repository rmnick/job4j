package ru.job4j.foodstorage.sorters;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.storage.Storage;

import java.time.ZoneId;
import java.util.List;

public class TrashSorter extends AbstractSorter {
    public TrashSorter(Storage storage) {
        super(storage);
    }

    @Override
    public void sort(List<Food> foods) {
        foods.forEach(food -> {
            long expireDate = food.getExpireDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long now = System.currentTimeMillis();
            long dif = (expireDate - now);
            if (dif < 0) {
                this.storage.getStorage().add(food);
            }
        });
    }

    @Override
    public String toString() {
        return String.format("trashSorter dispatches to %s storage", this.storage.toString());
    }
}
