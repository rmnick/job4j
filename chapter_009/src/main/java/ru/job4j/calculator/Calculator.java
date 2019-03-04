package ru.job4j.calculator;

import ru.job4j.calculator.calculation.ICalculation;
import ru.job4j.calculator.calculation.arithmetic.AbstractArithmetic;


public class Calculator {
    private final IInput input;
    private final IOutput output;
    private final Validator validator;

    public Calculator(final IInput input, final IOutput output, final Validator validator) {
        this.input = input;
        this.output = output;
        this.validator = validator;
    }

    public void run() {
        String answer;
        double var = 0;
        do {
            output.out(String.format("%.4f", var));
            answer = this.input.ask();
            if (validator.checkNumber(answer)) {
                var = Double.valueOf(answer);
                output.out(String.valueOf(var));
                var = execute(var, this.input.ask());
            } else if (validator.checkOperation(answer)) {
                var = execute(var, answer);
            }
        } while (!answer.trim().toLowerCase().equals("off"));
    }

    public double execute(double firstNumber, String answer) {
        double result;
        double secondNumber = 0;
        ICalculation calculation = validator.getOperation(answer);
        if (!(calculation instanceof AbstractArithmetic)) {
            output.out(String.format("%s%.2f", calculation, firstNumber));
            result = calculation.calc(new double[]{firstNumber, secondNumber});
        } else {
            output.out(String.format("%.2f %s", firstNumber, calculation));
            secondNumber = validator.getNumber(this.input.ask());
            output.out(String.format("%.2f %s %.2f", firstNumber, calculation, secondNumber));
            result = calculation.calc(new double[]{firstNumber, secondNumber});
        }
        return result;
    }
}
