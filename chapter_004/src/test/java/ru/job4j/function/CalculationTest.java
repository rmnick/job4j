package ru.job4j.function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CalculationTest {
    @Test
    public void testWhenLinear() {
        Calculation calc = new Calculation();
        List<Double> list = new ArrayList<>();
        list = calc.diapason(0,  5, (index) -> (double) index);
        assertThat(list, is(Arrays.asList(0D, 1D, 2D, 3D, 4D, 5D)));
    }
    @Test
    public void testWhenQuadratic() {
        Calculation calc = new Calculation();
        List<Double> list = new ArrayList<>();
        list = calc.diapason(0, 5, (index) -> Math.pow((double) index, 2));
        assertThat(list, is(Arrays.asList(0D, 1D, 4D, 9D, 16D, 25D)));
    }
    @Test
    public void testWhenLogarithmic() {
        Calculation calc = new Calculation();
        List<Double> list = new ArrayList<>();
        list = calc.diapason(1, 4, (index) -> calc.log(index, 2));
        List<Double> example = new ArrayList<>();
        calc.doExample(example, calc::log);
        assertThat(list, is(example));
    }
}
