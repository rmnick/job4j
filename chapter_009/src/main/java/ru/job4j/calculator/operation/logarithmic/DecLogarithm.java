package ru.job4j.calculator.operation.logarithmic;

public class DecLogarithm extends AbstractLogarithm {
    public DecLogarithm(String name) {
        super(name);
    }

    @Override
    public double calc(double[] var) {
        double result = 0;
        if (var[0] != 0) {
            result = Math.log(var[0]);
        }
        return result;
    }
}
