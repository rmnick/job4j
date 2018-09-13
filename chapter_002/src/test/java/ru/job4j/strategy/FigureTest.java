package ru.job4j.strategy;
/**
 * @author Rodionov Nick (mailto:r.m.nick@yandex.ru)
 * @version $Id$
 * @since 2018.08.25
 */
import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FigureTest {
    @Test
            public void whenDrawSquareThenSquare() {
        Square sqr = new Square();
        assertThat(sqr.draw(), is(new StringJoiner(System.lineSeparator())
                .add("+ + + +")
                .add("+     +")
                .add("+     +")
                .add("+ + + +")
                .toString()
                )
        );
    }
    @Test
    public void whenDrawTriangleThenTriangle() {
        Triangle triangle = new Triangle();
        assertThat(triangle.draw(), is(new StringJoiner(System.lineSeparator())
                .add("      +")
                .add("    + +")
                .add("  + + +")
                .add("+ + + +")
                .toString()
                )
        );
    }
}
