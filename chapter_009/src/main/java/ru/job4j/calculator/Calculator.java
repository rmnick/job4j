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
        double[] var = {0, 2};
        String key;
        do {
            answer = this.input.ask();
            if (answer.trim().matches("^[0-9]*[.,]?[0-9]+$")) {
                System.out.println("digit");
                var[0] = Double.valueOf(answer.trim());
            } else {
                System.out.println("not digit");
                key = answer.trim();
                IArithmeticOperation operation = operations.get(key);
                while (operation == null) {
                    System.out.println("input right operation symbol");
                    answer = this.input.ask();
                    operation = operations.get(answer);
                }
                answer = this.input.ask();
                while (!answer.trim().matches("^[0-9]*[.,]?[0-9]+$")) {
                    System.out.println("input right number");
                    answer = this.input.ask();
                }
                var[1] = Double.valueOf(answer);
                System.out.println(operation.calc(var));
            }

        } while (!answer.equals("exit"));
    }
}
