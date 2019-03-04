package ru.job4j.calculator;


public class Calculator {
    private final IInput input;
    private final IOutput output;
    private final Validator validator;

    public Calculator(final IInput input, final IOutput output, final Validator validator) {
        this.input = input;
        this.output = output;
        this.validator = validator;
    }

    /**
     * use input-output to communicate whit program
     * choose right operation for calculation or leaving program
     * if first word from user will be number then use this number as firstNumber for calculation
     * if first word will be some operation then use result as firstNumber for calculation
     */
    public void run() {
        String answer;
        String result = "0";
        do {
            output.out(result);
            answer = this.input.ask();
            if (validator.checkNumber(answer)) {
                output.out(answer);
                result = validator.getOperation(this.input.ask()).execute(answer, input, output, validator);
            } else if (validator.checkOperation(answer)) {
                result = validator.getOperation(answer).execute(result, input, output, validator);
            }
        } while (!(answer.trim().toLowerCase().equals(Start.OFF) || result.equals(Start.OFF)));
    }
}
