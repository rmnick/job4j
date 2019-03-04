package ru.job4j.calculator.operation.arithmetic;


public class Division extends AbstractArithmetic {

    public Division(String name) {
        super(name);
    }

    @Override
    public double calc(final double[] var) {
        double result = 0;
        if (var[1] != 0) {
            result = var[0] / var[1];
        }
        return result;
    }

}
