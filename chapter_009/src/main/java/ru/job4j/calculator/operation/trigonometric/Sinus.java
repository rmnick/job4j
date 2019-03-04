package ru.job4j.calculator.operation.trigonometric;

public class Sinus extends AbstractTrigonometric {
    public Sinus(String name) {
        super(name);
    }

    @Override
    public double calc(double[] var) {
        return Math.sin(var[0]);
    }
}
