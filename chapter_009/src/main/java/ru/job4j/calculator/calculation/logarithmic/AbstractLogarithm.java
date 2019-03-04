package ru.job4j.calculator.calculation.logarithmic;

import ru.job4j.calculator.calculation.ICalculation;

public abstract class AbstractLogarithm implements ICalculation {
    protected final String name;


    public AbstractLogarithm(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
