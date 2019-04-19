package ru.job4j.foodstorage.food;

import java.time.LocalDateTime;

public class Beer extends Food {
    public Beer(double price, LocalDateTime createDate, LocalDateTime expireDate, int discount, String name, boolean canReproduct) {
        super(price, createDate, expireDate, discount, name, canReproduct);
    }
}
