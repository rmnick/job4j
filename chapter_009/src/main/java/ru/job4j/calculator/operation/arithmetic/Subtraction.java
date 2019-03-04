package ru.job4j.calculator.operation.arithmetic;

public class Subtraction extends AbstractArithmetic {

    public Subtraction(String name) {
        super(name);
    }

    @Override
    public double calc(final double[] var) {
        return var[0] - var[1];
    }

}
