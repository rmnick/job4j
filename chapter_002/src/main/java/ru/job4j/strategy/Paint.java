package ru.job4j.strategy;
/**
 * @author Rodionov Nick (mailto:r.m.nick@yandex.ru)
 * @version $Id$
 * @since 2018.08.25
 */
public class Paint {
    private Shape shape;

    public Paint(Shape shape) {
        this.shape = shape;
    }

    public String drawPerform() {
        return this.shape.draw();
    }

    public static void main(String[] args) {
        Paint square = new Paint(new Square());
        System.out.println(square.drawPerform());
        Paint triangle = new Paint(new Triangle());
        System.out.println(triangle.drawPerform());
    }

}
