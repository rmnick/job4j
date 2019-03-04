package ru.job4j.calculator.operation.arithmetic;

import ru.job4j.calculator.IInput;
import ru.job4j.calculator.IOutput;
import ru.job4j.calculator.Validator;
import ru.job4j.calculator.operation.ICalculation;

public abstract class AbstractArithmetic implements ICalculation {
    protected final String name;


    public AbstractArithmetic(final String name) {
        this.name = name;
    }

    @Override
    public String execute(String firstNumber, IInput input, IOutput output, Validator validator) {
        String secondNumber;
        output.out(String.format("%s %s", firstNumber, name));
        secondNumber = validator.getNumber(input.ask());
        output.out(String.format("%s %s %s", firstNumber, name, secondNumber));
        return String.valueOf(calc(new double[]{Double.valueOf(firstNumber), Double.valueOf(secondNumber)}));
    }

    @Override
    public String toString() {
        return name;
    }


}
