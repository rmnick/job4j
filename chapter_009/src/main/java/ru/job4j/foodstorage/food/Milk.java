package ru.job4j.foodstorage.food;

import java.time.LocalDateTime;

public class Milk extends Food {
    public Milk(double price, LocalDateTime createDate, LocalDateTime expireDate, int discount, String name) {
        super(price, createDate, expireDate, discount, name);
    }
}
