package ru.job4j.foodstorage.storage;


import ru.job4j.foodstorage.food.Food;

public interface IStorage {
    boolean check(Food food);
}
