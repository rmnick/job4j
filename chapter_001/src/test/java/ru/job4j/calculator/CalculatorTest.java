package ru.job4j.calculator;

import org.junit.Test;
import static  org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * CalculatorTest
 *
 * @author Rodionov Nick (r.m.nick@yandex.ru)
 * @version 1.0
 * @since 2018/08/04
 */
public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
    @Test
    public void whenSubtractTwofromThreeThenOne() {
        Calculator calc = new Calculator();
        calc.subtract(3D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }
    @Test
    public void whenDivTwoOnTwoThenOne() {
        Calculator calc = new Calculator();
        calc.div(2D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }
    @Test
    public void whenMultiplyTwoonTwoThenFour() {
        Calculator calc = new Calculator();
        calc.multiply(2D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }
}
