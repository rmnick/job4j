package ru.job4j.bank;

import java.util.StringJoiner;

public class Account {
    private double value;
    private final String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public String toString() {
        return new StringJoiner("; ", "(", ")")
                .add(String.valueOf(value))
                .add(requisites)
                .toString();
    }
}
