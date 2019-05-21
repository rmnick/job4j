package ru.job4j.service;

public class Seat {
    private int id;
    private int number;
    private int row;
    private boolean booked;
    private int price;


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

//    public void setId(int id) {
//        this.id = id;
//    }

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
        return String.format("Hall{number=%d, row=%d, price=%d}",
                this.number, this.row, this.price);
    }
}
