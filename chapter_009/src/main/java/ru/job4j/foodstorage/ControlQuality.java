package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.sorters.ISorter;
import ru.job4j.foodstorage.storage.IStorage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<IStorage> storages = new ArrayList<>();

//    public void setSorter(final ISorter controller) {
//        this.sorter = controller;
//    }

    public void add(IStorage storage) {
        this.storages.add(storage);
    }

    public void sort(List<Food> food) {

    }
}
