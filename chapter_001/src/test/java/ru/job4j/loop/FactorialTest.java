package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    @Test
    public void whenNumberEqualsZeroThenOne() {
        Factorial fact = new Factorial();
        assertThat(fact.calc(0), is(1));
    }
    @Test
    public void calculationFactorial() {
        Factorial fact = new Factorial();
        assertThat(fact.calc(5), is(120));
    }
}
