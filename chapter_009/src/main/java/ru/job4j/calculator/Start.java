package ru.job4j.calculator;

public class Start {
    public static void main(String[] args) {
        IInput input = new ConsoleInput();
        IOutput output = new ConsoleOutput();
        Calculator calc = new Calculator(input, output);
        calc.run();
    }
}
