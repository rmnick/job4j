package ru.job4j.service;

import java.util.StringJoiner;

public class Account {
    private String name;
    private String phone;
    private String seatId;
    private String id;

    public Account() {
    }

    public Account(final String name, final String phone, final String seatId) {
        this.name = name;
        this.phone = phone;
        this.seatId = seatId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSeatId(String seatId) {
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

    public String getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
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
