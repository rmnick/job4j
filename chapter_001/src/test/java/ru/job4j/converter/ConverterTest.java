package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConverterTest {
    @Test
    public void when140RublesToEurosThen2() {
        Converter conv = new Converter();
        int result = conv.rublesToEuros(140);
        assertThat(result, is(2));
    }
    @Test
    public void when120RublesToDollarsThen2() {
        Converter conv = new Converter();
        int result = conv.rublesToDollars(120);
        assertThat(result, is(2));
    }
    @Test
    public void when3EurosToRublesThen210() {
        Converter conv = new Converter();
        int result = conv.eurosToRubles(3);
        assertThat(result, is(210));
    }
    @Test
    public void when3DollarsToRublesThen180() {
        Converter conv = new Converter();
        int result = conv.dollarsToRubles(3);
        assertThat(result, is(180));
    }
}
