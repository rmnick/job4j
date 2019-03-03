package ru.job4j.calculator;

import ru.job4j.calculator.calculation.ICalculation;


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
            output.out(String.valueOf(var));
            answer = this.input.ask();
            if (validator.checkNumber(answer)) {
                var = Double.valueOf(answer);
                output.out(String.valueOf(var));
                var = validator.getOperation(this.input.ask()).execute(var);
            } else if (validator.checkOperation(answer)) {
                var = validator.getOperation(this.input.ask()).execute(var);
            }
        } while (!answer.trim().toLowerCase().equals("off"));
    }
}
