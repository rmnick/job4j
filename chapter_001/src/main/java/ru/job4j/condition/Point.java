package ru.job4j.condition;
/**
 * smart point.
 *
 * @author Rodionov Nick (r.m.nick@yandex.ru).
 * @version 1.0.
 * @since 2018/08/04.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Find the distance from this to another(that) point.
     * math equation: distance(a(x,y) to b(x,y))=((a(x)-b(x))^2 + (a(y)-b(y))^2)^1/2 .
     * @param that Point.
     * @return the distance.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }
}
