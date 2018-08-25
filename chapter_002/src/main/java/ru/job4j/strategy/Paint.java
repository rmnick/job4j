package ru.job4j.strategy;
/**
 * @author Rodionov Nick (mailto:r.m.nick@yandex.ru)
 * @version $Id$
 * @since 2018.08.25
 */
public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
