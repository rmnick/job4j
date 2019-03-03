package ru.job4j.calculator.calculation.arithmetic;

import ru.job4j.calculator.IInput;
import ru.job4j.calculator.IOutput;
import ru.job4j.calculator.Validator;
import ru.job4j.calculator.calculation.ICalculation;

public abstract class AbstractArithmetic implements ICalculation {
    protected final IInput input;
    protected final IOutput output;
    protected final String name;
    protected final Validator validator;

    public AbstractArithmetic(final IInput input, final IOutput output, final String name, final Validator validator) {
        this.input = input;
        this.output = output;
        this.name = name;
        this.validator = validator;
    }

    public double execute(final double firstNumber) {
        String answer;
        output.out(String.format("%.2f %s", firstNumber, name));
        answer = this.input.ask();
        double secondNumber = validator.getNumber(answer);
        output.out(String.format("%.2f %s %.2f", firstNumber, name, secondNumber));
        return calc(new double[]{firstNumber, secondNumber});
    }


}
