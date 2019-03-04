package ru.job4j.calculator.operation.trigonometric;

public class Tangens extends AbstractTrigonometric {
    public Tangens(String name) {
        super(name);
    }

    @Override
    public double calc(double[] var) {
        return Math.tan(var[0]);
    }
}
