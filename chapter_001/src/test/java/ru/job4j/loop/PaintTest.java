package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.StringJoiner;

public class PaintTest {
    @Test
    public void whenHeightIsTwo() {
        Paint paint = new Paint();
        String ln = System.lineSeparator();
        String rsl = paint.piramid(2);
        assertThat(rsl, is(new StringJoiner(ln, "", ln).add(" ^ ").add("^^^").toString()));
    }
    @Test
    public void whenHeightIsThree() {
        Paint paint = new Paint();
        String ln = System.lineSeparator();
        String rsl = paint.piramid(3);
        assertThat(rsl, is(new StringJoiner(ln, "", ln).add("  ^  ").add(" ^^^ ").add("^^^^^").toString()));
    }
}
