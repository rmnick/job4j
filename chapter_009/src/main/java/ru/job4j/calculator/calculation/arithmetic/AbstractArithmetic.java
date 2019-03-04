package ru.job4j.calculator.calculation.arithmetic;

import ru.job4j.calculator.calculation.ICalculation;

public abstract class AbstractArithmetic implements ICalculation {
    protected final String name;


    public AbstractArithmetic(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
