package ru.job4j.calculator;

import ru.job4j.calculator.arithmetic.Addition;
import ru.job4j.calculator.arithmetic.Division;
import ru.job4j.calculator.arithmetic.IArithmeticOperation;
import ru.job4j.calculator.arithmetic.Multiplication;

import java.util.HashMap;
import java.util.Map;

public class Start {
    private final Map<String, IArithmeticOperation> operations = new HashMap<>();

    public void fill() {
        operations.put("+", new Addition());
        operations.put("-", new Division());
        operations.put("/", new Division());
        operations.put("*", new Multiplication());
    }

    public static void main(String[] args) {
        Start st = new Start();
        st.fill();
        IInput input = new ConsoleInput();
        IOutput output = new ConsoleOutput();
        Calculator calc = new Calculator(input, output, st.operations);
        calc.run();
    }
}
