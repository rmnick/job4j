package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.storage.IStorage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<IStorage> storage = new ArrayList<>();

    public void add(IStorage st) {
        this.storage.add(st);
    }

    /**
     * each storage in the list checks the product from the product list
     * adding according with storage conditions
     * @param foodstuff List<Food>
     */
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

    public List<IStorage> getStorage() {
        return this.storage;
    }
}
