package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point a = new Point(1, 1);
        Point b = new Point(3, 3);
        Point c = new Point(4, 1);
        Triangle tr = new Triangle(a, b, c);
        double result = tr.area();
        double expected = 2.9999;
        assertThat(result, closeTo(expected, 0.02));
    }
}
