package ru.job4j.foodstorage.food;

import java.time.LocalDateTime;

public class Cheese extends Food {
    public Cheese(double price, LocalDateTime createDate, LocalDateTime expireDate, int discount) {
        super(price, createDate, expireDate, discount);
    }
}