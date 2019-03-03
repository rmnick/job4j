package ru.job4j.calculator.calculation.arithmetic;

import ru.job4j.calculator.IInput;
import ru.job4j.calculator.IOutput;
import ru.job4j.calculator.Validator;

public class Division extends AbstractArithmetic {

    public Division(IInput input, IOutput output, String name, Validator validator) {
        super(input, output, name, validator);
    }

    @Override
    public double calc(final double[] var) {
        return var[0] / var[1];
    }

    @Override
    public String toString() {
        return name;
    }
}
