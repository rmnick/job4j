package ru.job4j.calculator.arithmetic;

public class Multiplication implements IArithmeticOperation {

    @Override
    public double calc(final double[] var) {
        return var[0] * var[1];
    }
}
