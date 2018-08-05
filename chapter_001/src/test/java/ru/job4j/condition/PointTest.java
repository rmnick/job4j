package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void distanceTo() {
        Point a = new Point(1, 3);
        Point b = new Point(2, 4);
        double dist = a.distanceTo(b);
        assertThat(dist, closeTo(1.41, 0.02));
    }
}
