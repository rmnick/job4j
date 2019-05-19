package ru.job4j.service;

public class Seat {
    private int id;
    private int number;
    private int row;
    private boolean booked;
    private int price;

    public Seat(final int number, final int row) {
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

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return String.format("Hall{number=%s, row=%s}",
                this.number, this.row);
    }
}
