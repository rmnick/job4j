package ru.job4j.foodstorage.food;

import java.time.LocalDateTime;

public class Tomatoes extends Food {
    public Tomatoes(double price, LocalDateTime createDate, LocalDateTime expireDate, int discount) {
        super(price, createDate, expireDate, discount);
    }
}
