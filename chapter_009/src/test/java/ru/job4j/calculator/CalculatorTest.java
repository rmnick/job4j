package ru.job4j.calculator;

import org.junit.Test;
import ru.job4j.calculator.operation.ICalculation;
import ru.job4j.calculator.operation.arithmetic.Addition;
import ru.job4j.calculator.operation.arithmetic.Subtraction;
import ru.job4j.calculator.operation.logarithmic.DecLogarithm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CalculatorTest {
    @Test
    public void whenTwoPlusTwoThenFour() {
        ICalculation add = new Addition("+");
        assertThat(add.calc(new double[]{2.0, 2.0}), is(4.0));
    }

    @Test
    public void whenTwoSubtractFiveThenMinusThree() {
        ICalculation sub = new Subtraction("-");
        assertThat(sub.calc(new double[]{2.0, 5.0}), is(-3.0));
    }

    @Test
    public void whenLogOneThenZero() {
        ICalculation log = new DecLogarithm("log");
        assertThat(log.calc(new double[]{1.0}), is(0.0));
    }
}
