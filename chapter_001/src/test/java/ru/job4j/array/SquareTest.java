package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void whenBoundIs4Then14916() {
        Square sqr = new Square();
        int[] rst = sqr.calculate(4);
        assertThat(rst, is(new int[]{1, 4, 9, 16}));
    }
    @Test
    public void whenBoundIs1Then1() {
        Square sqr = new Square();
        int[] rst = sqr.calculate(1);
        assertThat(rst, is(new int[]{1}));
    }
    @Test
    public void whenBoundIs7Then14916253649() {
        Square sqr = new Square();
        int[] rst = sqr.calculate(7);
        assertThat(rst, is(new int[]{1, 4, 9, 16, 25, 36, 49}));
    }
}

