package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class FrigeStore extends AbstractDecoratorStorage {

    private final List<String> vegetables = new ArrayList<>();

    public FrigeStore(String name, AbstractStorage store) {
        super(name, store);
        this.fill();
    }

    private void fill() {
        vegetables.add("tomatoes");
        vegetables.add("potato");
        vegetables.add("onion");
    }

    @Override
    public boolean check(Food item) {
        boolean result = false;
        if (store.check(item) && this.isVegetable(item.getClass().getSimpleName())) {
            result = true;
        }
        return result;
    }

    public boolean isVegetable(String name) {
        boolean result = false;
        for (String item : vegetables) {
            if (item.equals(name.toLowerCase())) {
                result = true;
                break;
            }
        }
        return result;
    }
}
