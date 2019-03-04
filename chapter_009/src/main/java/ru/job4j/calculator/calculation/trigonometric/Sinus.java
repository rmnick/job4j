package ru.job4j.calculator.calculation.trigonometric;

public class Sinus extends Abstractrigonometric {
    public Sinus(String name) {
        super(name);
    }

    @Override
    public double calc(double[] var) {
        return Math.sin(var[0]);
    }
}
