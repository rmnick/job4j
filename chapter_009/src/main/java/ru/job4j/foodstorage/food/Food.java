package ru.job4j.foodstorage.food;

import java.sql.Date;
import java.time.LocalDateTime;

public class Food {
    public final double price;
    public final LocalDateTime createDate;
    public final LocalDateTime expireDate;
    protected int discount;
    public final String name;
    public final boolean canReproduct;

    public Food(final double price, final LocalDateTime createDate, final LocalDateTime expireDate, final int discount, final String name, final boolean canReproduct) {
        this.price = price;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.discount = discount;
        this.name = name;
        this.canReproduct = canReproduct;
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

    public boolean isCanReproduct() {
        return this.canReproduct;
    }

    @Override
    public String toString() {
        return String.format("(%s; price: %.2f; expireDate: %s; discount: %d%s)", name, price, Date.valueOf(expireDate.toLocalDate()), discount, "%");
    }
}
