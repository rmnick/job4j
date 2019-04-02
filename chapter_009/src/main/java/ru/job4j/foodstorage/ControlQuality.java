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
        for (IStorage st : storage) {
            for (int i = 0; i < foodstuff.size(); i++) {
                if (st.check(foodstuff.get(i))) {
                    st.add(foodstuff.get(i));
                    foodstuff.remove(i);
                    break;
                }
            }
        }
    }

    public List<IStorage> getStorage() {
        return this.storage;
    }
}
