package ru.job4j.service;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String name;
    private final String phone;
    private final List<Seat> seats = new ArrayList<>();

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

    public Seat addSeat(final Seat seat) {
        this.seats.add(seat);
        return seat;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }
}
