package ru.job4j.calculator;

import ru.job4j.calculator.operation.IOperation;
import ru.job4j.calculator.operation.arithmetic.Addition;
import ru.job4j.calculator.operation.arithmetic.Division;
import ru.job4j.calculator.operation.arithmetic.Multiplication;
import ru.job4j.calculator.operation.arithmetic.Subtraction;
import ru.job4j.calculator.operation.logarithmic.DecLogarithm;
import ru.job4j.calculator.operation.system.OffOperation;
import ru.job4j.calculator.operation.system.ResetOperation;
import ru.job4j.calculator.operation.trigonometric.Cosinus;
import ru.job4j.calculator.operation.trigonometric.Sinus;
import ru.job4j.calculator.operation.trigonometric.Tangens;

import java.util.HashMap;
import java.util.Map;

public class Start {
    private final Map<String, IOperation> operations = new HashMap<>();
    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String DIV = ":";
    public static final String MUL = "*";
    public static final String COS = "cos";
    public static final String SIN = "sin";
    public static final String TAN = "tan";
    public static final String LOG = "log";
    public static final String CE = "c";
    public static final String OFF = "off";


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
            operations.put(CE, new ResetOperation(CE));
            operations.put(OFF, new OffOperation(OFF));

        }

        public void fillExtended() {
            operations.put(SIN, new Sinus(SIN));
            operations.put(COS, new Cosinus(COS));
            operations.put(TAN, new Tangens(TAN));
            operations.put(LOG, new DecLogarithm(LOG));
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
                output.out(String.format("%s %s %s %s %s %s", ADD, SUB, DIV, MUL, CE, OFF));
            } else {
                fillStandard();
                fillExtended();
                output.out(String.format("%s %s %s %s %s %s %s %s %s %s", ADD, SUB, DIV, MUL, SIN, COS, TAN, LOG, CE, OFF));

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
