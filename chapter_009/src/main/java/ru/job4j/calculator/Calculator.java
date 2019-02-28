package ru.job4j.calculator;

import ru.job4j.calculator.arithmetic.IArithmeticOperation;

import java.util.Map;

public class Calculator {
    private final IInput input;
    private final IOutput output;
    private final Map<String, IArithmeticOperation> operations;

    public Calculator(final IInput input, final IOutput output, final Map<String, IArithmeticOperation> operations) {
        this.input = input;
        this.output = output;
        this.operations = operations;
    }

    public void run() {
        String answer = null;
        double[] var = {0, 0};
        do {
            answer = this.input.ask();

        } while (!answer.equals("exit"));
    }
}
