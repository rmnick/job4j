package ru.job4j.condition;
/**
 * Square of triangle.
 *
 * @author Rodionov Nick (r.m.nick@yandex.ru).
 * @version 1.0.
 * @since 2018/08/07.
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    /**
     * Possibility to exist the triangle with these sides.
     * If side(ab)+side(bc)>side(ac) and bc+ac>ab and ab+ac>bc then the triangle exists.
     *
     * @param ab length from a to b.
     * @param ac length from a to c.
     * @param bc length from b to c.
     * @return boolean possibility to exist.
     */
    private boolean exist(double ab, double ac, double bc) {
        return ((ab + bc > ac) && (ab + ac > bc) && (ac + bc > ab));
    }
    /**
     * Half the perimeter.
     * (ab + ac + bc) / 2
     *
     * @param ab length from a to b.
     * @param ac length from a to c.
     * @param bc length from b to c.
     * @return perimeter.
     */
    public double perimeter(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }
    /**
     * Area of triangle.
     * We use Heron's formula.
     * @return value of area or -1 if the triangle doesn't exist.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.perimeter(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            // написать формулу для расчета площади треугольника.
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }
}
