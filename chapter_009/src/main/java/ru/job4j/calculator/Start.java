package ru.job4j.calculator;

import ru.job4j.calculator.calculation.ICalculation;
import ru.job4j.calculator.calculation.arithmetic.Addition;
import ru.job4j.calculator.calculation.arithmetic.Division;
import ru.job4j.calculator.calculation.arithmetic.Multiplication;
import ru.job4j.calculator.calculation.arithmetic.Subtraction;

import java.util.HashMap;
import java.util.Map;

public class Start {
    private final Map<String, ICalculation> operations = new HashMap<>();
    private final static String ADD = "+";
    private final static String SUB = "-";
    private final static String DIV = "/";
    private final static String MUL = "*";

    public void fill(IInput input, IOutput output, Validator validator) {
        operations.put(ADD, new Addition(input, output, ADD, validator));
        operations.put(SUB, new Subtraction(input, output, SUB, validator));
        operations.put(DIV, new Division(input, output, DIV, validator));
        operations.put(MUL, new Multiplication(input, output, MUL, validator));
    }

    public static void main(String[] args) {
        Start st = new Start();
        IInput input = new ConsoleInput();
        IOutput output = new ConsoleOutput();
        Validator validator = new Validator(st.operations, input, output);
        Calculator calc = new Calculator(input, output, validator);
        calc.run();
    }
}
