package ru.job4j.calculator;

import ru.job4j.calculator.calculation.ICalculation;
import ru.job4j.calculator.calculation.arithmetic.Addition;
import ru.job4j.calculator.calculation.arithmetic.Division;
import ru.job4j.calculator.calculation.arithmetic.Multiplication;
import ru.job4j.calculator.calculation.arithmetic.Subtraction;
import ru.job4j.calculator.calculation.trigonometric.Sinus;

import java.util.HashMap;
import java.util.Map;

public class Start {
    private final Map<String, ICalculation> operations = new HashMap<>();
    private final static String ADD = "+";
    private final static String SUB = "-";
    private final static String DIV = "/";
    private final static String MUL = "*";
    private final static String COS = "cos";
    private final static String SIN = "sin";
    private final static String TAN = "tan";
    private final static String LOG = "log";


    public class Menu {
        private final IInput input;
        private final IOutput output;

        public Menu(final IInput input, final IOutput output) {
            this.input = input;
            this.output = output;
        }

        public void fillStandard() {
            operations.put(ADD, new Addition(ADD));
            operations.put(SUB, new Subtraction(SUB));
            operations.put(DIV, new Division(DIV));
            operations.put(MUL, new Multiplication(MUL));

        }

        public void fillExtended() {
            operations.put(SIN, new Sinus(SIN));
            operations.put(COS, new Sinus(COS));
            operations.put(TAN, new Sinus(TAN));
            operations.put(LOG, new Sinus(LOG));
        }

        public void show() {
            output.out("choose your functionality : standard or extended");
            String answer = input.ask();
            while (!(answer.trim().toLowerCase().equals("standard") || answer.trim().toLowerCase().equals("extended"))) {
                output.out("input correct option");
                answer = input.ask();
            }
            if (answer.equals("standard")) {
                fillStandard();
            } else {
                fillStandard();
                fillExtended();
            }
        }
    }

    public static void main(String[] args) {
        Start st = new Start();
        IInput input = new ConsoleInput();
        IOutput output = new ConsoleOutput();
        Menu menu = st.new Menu(input, output);
        menu.show();
        Validator validator = new Validator(st.operations, input, output);
        Calculator calc = new Calculator(input, output, validator);
        calc.run();
    }
}
