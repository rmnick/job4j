package ru.job4j.factorial;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class FactorialIterTest {
    @Test
    public void whenNumEqualsZeroThenResultEqualsOne() {
        IFactorial iter = new FactorialIter();
        int numOne = 0;
        int realOne = iter.calc(numOne);
        assertThat(realOne, is(1));
    }

    @Test
    public void whenNumEqualsSomeNumberThenResultEqualsNumFactorial() {
        IFactorial iter = new FactorialIter();
        int numOne = 5;
        int numTwo = 8;
        int expectOne = 1 * 2 * 3 * 4 * 5;
        int expectTwo = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8;
        int realOne = iter.calc(numOne);
        int realTwo = iter.calc(numTwo);
        assertThat(realOne, is(expectOne));
        assertThat(realTwo, is(expectTwo));
    }

    @Test
    public void whenNumEqualsSomeNumberThenResultIsNotEqualNumFactorial() {
        IFactorial iter = new FactorialIter();
        int numOne = 5;
        int expectOne = 1 * 2 * 3 * 4;
        int realOne = iter.calc(numOne);
        assertThat(realOne, is(not(expectOne)));
    }
}
