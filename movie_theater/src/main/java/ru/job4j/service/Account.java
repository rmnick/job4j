package ru.job4j.service;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String name;
    private final String phone;
    private Seat seat;

    public Account(final String name, final String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setSeat(final Seat seat) {
        this.seat = seat;

    }

    public Seat getSeat() {
        return this.seat;
    }

    @Override
    public String toString() {
        return String.format("name: %s, phone: %s", this.name, this.phone);
    }
}
