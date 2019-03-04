package ru.job4j.calculator.calculation.trigonometric;

public class Tangens extends Abstractrigonometric {
    public Tangens(String name) {
        super(name);
    }

    @Override
    public double calc(double[] var) {
        return Math.tan(var[0]);
    }
}
