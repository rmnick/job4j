package ru.job4j.service;

import java.util.StringJoiner;

public class Account {
    private String name;
    private String phone;
    private String seatId;

    public Account() {
    }

    public Account(final String name, final String phone, final String seatId) {
        this.name = name;
        this.phone = phone;
        this.seatId = seatId;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getSeatId() {
        return this.seatId;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        sj.add(name);
        sj.add(phone);
        sj.add(seatId);
        return sj.toString();
    }
}
