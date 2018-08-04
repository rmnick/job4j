package ru.job4j.calculator;
/**
 * Calculator
 *
 * @author Rodionov Nick (r.m.nick@yandex.ru)
 * @version 1.0
 * @since 2018/08/04
 */
public class Calculator {
    private double result;

    public void add(double first, double second) {
        this.result = first + second;
    }

    public void subtract(double first, double second) {
        this.result = first - second;
    }

    public void multiply(double first, double second) {
        this.result = first * second;
    }

    public void div(double first, double second) {
        this.result = first / second;
    }

    public double getResult() {
        return this.result;
    }
}
