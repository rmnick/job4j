package ru.job4j.calculator.operation.arithmetic;


public class Multiplication extends AbstractArithmetic {

    public Multiplication(String name) {
        super(name);
    }

    @Override
    public double calc(final double[] var) {
        return var[0] * var[1];
    }

}
