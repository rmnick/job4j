package ru.job4j.foodstorage.food;

import java.time.LocalDateTime;

public class Food {
    public final double price;
    public final LocalDateTime createDate;
    public final LocalDateTime expireDate;
    protected int discount;

    public Food(final double price, final LocalDateTime createDate, final LocalDateTime expireDate, final int discount) {
        this.price = price;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
