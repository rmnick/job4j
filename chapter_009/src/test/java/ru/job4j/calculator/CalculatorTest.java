package ru.job4j.calculator;

import org.junit.Test;
import ru.job4j.calculator.calculation.ICalculation;
import ru.job4j.calculator.calculation.arithmetic.Subtraction;

public class CalculatorTest {
    @Test
    public void test() {
        //BigDecimal a = BigDecimal.valueOf(36);
        //BigDecimal b = BigDecimal.valueOf(54);
        //int a = 36;
        //int b = 54;
        //float a = 36;
        //float b = 54;
        String a = "36";
        String b = "54";
        //double a = 36.00;
        //double b = 54.00;
        System.out.println(Double.valueOf(a) - Double.valueOf(b));
    }

    @Test
    public void testOperation() {
        double[] var = {36.00, 54.00};
        ICalculation sub = new Subtraction("-");
        double result = sub.calc(var);
        System.out.println(result);
    }

    @Test
    public void scTest() {

    }
}
