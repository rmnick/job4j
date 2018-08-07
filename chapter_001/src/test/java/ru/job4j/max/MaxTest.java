package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenFirstBiggerThenSecondThenFirst() {
        Max maximum = new Max();
        assertThat(maximum.max(2, 1), is(2));
    }
    @Test
    public void whenSecondBiggerThenFirstThenSecond() {
        Max maximum = new Max();
        assertThat(maximum.max(3, 5), is(5));
    }
    @Test
    public void whenFirstEqualsSecondThenFirst() {
        Max maximum = new Max();
        assertThat(maximum.max(6, 6), is(6));
    }
}
