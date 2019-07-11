package ru.job4j.prime;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PrimeNumberCalculatorTest {
    @Test
    public void calcPrimeNumber() {
        PrimeNumberCalculator pnc = new PrimeNumberCalculator();
        int result = pnc.calc(10);
        assertThat(result, is(4));
    }
}
