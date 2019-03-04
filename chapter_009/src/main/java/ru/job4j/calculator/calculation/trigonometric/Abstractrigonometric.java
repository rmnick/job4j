package ru.job4j.calculator.calculation.trigonometric;

import ru.job4j.calculator.calculation.ICalculation;

public abstract class Abstractrigonometric implements ICalculation {
    protected final String name;


    public Abstractrigonometric(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
