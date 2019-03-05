package ru.job4j.foodstorage.sorters;

import ru.job4j.foodstorage.food.Food;

import java.util.List;

public interface ISorter {
    void sort(List<Food> foods);
}
