package ru.job4j.calculator.operation.trigonometric;

public class Cosinus extends AbstractTrigonometric {
    public Cosinus(String name) {
        super(name);
    }

    @Override
    public double calc(double[] var) {
        return Math.cos(var[0]);
    }
}
