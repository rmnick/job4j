package ru.job4j.foodstorage.food;

import java.time.LocalDateTime;

public class Cheese extends Food {
    public Cheese(double price, LocalDateTime createDate, LocalDateTime expireDate, int discount, String name, boolean canReproduct) {
        super(price, createDate, expireDate, discount, name, canReproduct);
    }
}
