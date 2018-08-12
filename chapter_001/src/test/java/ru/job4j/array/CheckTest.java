package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckTest {
    @Test
    public void whenAllAreFalse() {
        Check ch = new Check();
        boolean[] exmp = new boolean[] {false, false, false, false, false};
        assertThat(ch.mono(exmp), is(true));
    }
    @Test
    public void whenAllAreTrue() {
        Check ch = new Check();
        boolean[] exmp = new boolean[] {true, true, true, true};
        assertThat(ch.mono(exmp), is(true));
    }
    @Test
    public void whenOneIsTrueAndIsDiffer() {
        Check ch = new Check();
        boolean[] exmp = new boolean[] {false, false, true, false, false, false};
        assertThat(ch.mono(exmp), is(false));
    }
    @Test
    public void whenOneIsFalseAndIsDiffer() {
        Check ch = new Check();
        boolean[] exmp = new boolean[] {true, true, true, true, false};
        assertThat(ch.mono(exmp), is(false));
    }
}
