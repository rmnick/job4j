package ru.job4j.calculator.calculation.trigonometric;

public class Cosinus extends Abstractrigonometric {
    public Cosinus(String name) {
        super(name);
    }

    @Override
    public double calc(double[] var) {
        return Math.cos(var[0]);
    }
}
