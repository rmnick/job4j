package ru.job4j.calculator.arithmetic;

public class Addition implements IArithmeticOperation {

    @Override
    public double calc(double[] var) {
        return var[0] + var[1];
    }
}
