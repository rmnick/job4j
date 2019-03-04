package ru.job4j.calculator.calculation.arithmetic;


public class Division extends AbstractArithmetic {

    public Division(String name) {
        super(name);
    }

    @Override
    public double calc(final double[] var) {
        double result;
        if (var[1] == 0) {
            result = 0;
        } else {
           result = var[0] / var[1];
        }
        return result;
    }

}
