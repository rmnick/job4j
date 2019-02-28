package ru.job4j.calculator;

public class Calculator {
    private double result = 0;
    private final IInput input;
    private final IOutput output;

    public Calculator(final IInput input, final IOutput output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        String answer = null;
        do {
            answer = this.input.ask();
            this.output.out(answer);
        } while (!answer.equals("exit"));
    }
}
