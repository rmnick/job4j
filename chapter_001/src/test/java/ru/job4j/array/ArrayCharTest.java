package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayCharTest {
    @Test
    public void whenTrue() {
        ArrayChar arr = new ArrayChar("Welcome mr.X");
        assertThat(arr.startWith("Welcome "), is(true));
    }
    @Test
    public void whenFalse() {
        ArrayChar arr = new ArrayChar("Welcome mr.X");
        assertThat(arr.startWith("Welcame "), is(false));
    }
}
