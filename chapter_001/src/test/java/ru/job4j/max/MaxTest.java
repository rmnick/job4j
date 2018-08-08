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
    public void whenFirstEqualSecondThenFirst() {
        Max maximum = new Max();
        assertThat(maximum.max(6, 6), is(6));
    }
    @Test
    public void whenFirstBiggerThenOthersThenFirst() {
        Max maximum = new Max();
        assertThat(maximum.max(2, 1, 0), is(2));
    }
    @Test
    public void whenSecondBiggerThenOthersThenSecond() {
        Max maximum = new Max();
        assertThat(maximum.max(3, 5, 1), is(5));
    }
    @Test
    public void whenThirdBiggerThenOthersThenThird() {
        Max maximum = new Max();
        assertThat(maximum.max(3, 5, 8), is(8));
    }
    @Test
    public void whenFirstEqualSecondAndBothBiggerThenThirdThenFirst() {
        Max maximum = new Max();
        assertThat(maximum.max(6, 6, 4), is(6));
    }
    @Test
    public void whenAllEqualThenFirst() {
        Max maximum = new Max();
        assertThat(maximum.max(9, 9, 9), is(9));
    }
}
