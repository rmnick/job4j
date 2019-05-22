package ru.job4j.service;

import java.util.StringJoiner;

public class Seat {
    private int id;
    private int number;
    private int row;
    private boolean booked;
    private int price;
    private int accountId;

    public Seat() {
    }

    public Seat(final int id, final int number, final int row) {
        this.id = id;
        this.number = number;
        this.row = row;
    }

    public int getNumber() {
        return this.number;
    }

    public int getRow() {
        return this.row;
    }

    public int getId() {
        return id;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public int getAccountId() {
        return this.accountId;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        sj.add(String.valueOf(number));
        sj.add(String.valueOf(row));
        sj.add(String.valueOf(price));
        return sj.toString();
    }
}
