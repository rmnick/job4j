package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void turnArr() {
        Turn exmp = new Turn();
        int[] input = new int[] {3, 14, 18, 27, 34, 37, 41};
        int[] expect = new int[] {41, 37, 34, 27, 18, 14, 3};
        assertThat(exmp.turn(input), is(expect));
    }
}
