package ru.job4j.calculator.operation.arithmetic;


public class Addition extends AbstractArithmetic {

    public Addition(String name) {
        super(name);
    }

    @Override
    public double calc(double[] var) {
        return var[0] + var[1];
    }

}
