package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.storage.AbstractStorage;
import ru.job4j.foodstorage.storage.IStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * put all goods from internal store warehouses in new list
     * remove from internal store
     * @return List<Food>
     */
    private List<Food> recollectForResorting() {
        List<Food> result = new ArrayList<>();
        for (IStorage st : storage) {
            List<Food> list = st.getStorage();
            result.addAll(list);
            st.clearStorage();
        }
        return result;
    }

    public void resort() {
        this.sort(this.recollectForResorting());
    }

    public List<IStorage> getStorage() {
        return this.storage;
    }
}
