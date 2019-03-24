package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.sorters.ISorter;
import ru.job4j.foodstorage.storage.IStorage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<IStorage> storage = new ArrayList<>();

    public void add(IStorage st) {
        this.storage.add(st);
    }

    public void sort(List<Food> foodstuff) {
        foodstuff.forEach(item -> {
            for (IStorage st : storage) {
                if (st.check(item)) {
                    st.add(item);
                    break;
                }
            }
        });
    }
}
