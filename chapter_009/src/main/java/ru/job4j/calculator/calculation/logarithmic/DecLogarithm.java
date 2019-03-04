package ru.job4j.calculator.calculation.logarithmic;

public class DecLogarithm extends AbstractLogarithm {
    public DecLogarithm(String name) {
        super(name);
    }

    @Override
    public double calc(double[] var) {
        return Math.log(var[0]);
    }
}
