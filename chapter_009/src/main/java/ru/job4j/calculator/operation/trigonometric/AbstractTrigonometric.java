package ru.job4j.calculator.operation.trigonometric;

import ru.job4j.calculator.IInput;
import ru.job4j.calculator.IOutput;
import ru.job4j.calculator.Validator;
import ru.job4j.calculator.operation.ICalculation;

public abstract class AbstractTrigonometric implements ICalculation {
    protected final String name;


    public AbstractTrigonometric(final String name) {
        this.name = name;
    }

    @Override
    public String execute(String firstNumber, IInput input, IOutput output, Validator validator) {
        output.out(String.format("%s%s", name, firstNumber));
        return String.valueOf(calc(new double[]{Double.valueOf(firstNumber)}));
    }

    @Override
    public String toString() {
        return name;
    }
}
