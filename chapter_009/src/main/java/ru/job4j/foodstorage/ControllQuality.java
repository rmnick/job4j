package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.sorters.ISorter;

import java.util.List;

public class ControllQuality {
    private ISorter sorter;

    public void setSorter(final ISorter controller) {
        this.sorter = controller;
    }

    public void sort(List<Food> food) {
        this.sorter.sort(food);
    }
}
