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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return this.name.equals(account.getName())
                && this.phone.equals(account.getPhone())
                && this.id.equals(account.getId())
                && this.seatId.equals(account.getSeatId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + name.hashCode();
        result = result * 31 + phone.hashCode();
        result = result * 31 + id.hashCode();
        return result;
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
