package ru.job4j.calculator;

import ru.job4j.calculator.operation.IOperation;

import java.util.Map;

public class Validator {

    private final Map<String, IOperation> operations;
    private final static String REG_CHECK = "^[0-9]*[.,]?[0-9]+$";
    private final IInput input;
    private final IOutput output;

    public Validator(final Map<String, IOperation> operations, final IInput input, final IOutput output) {
        this.operations = operations;
        this.input = input;
        this.output = output;
    }

    public boolean checkNumber(String str) {
        return str.trim().matches(REG_CHECK);
    }

    public boolean checkOperation(String str) {
        return operations.get(str) == null ? false : true;
    }

    public IOperation getOperation(String str) {
        while (!checkOperation(str)) {
            str = this.input.ask();
        }
        return operations.get(str);
    }

    public String getNumber(String str) {
        while (!checkNumber(str)) {
            str = this.input.ask();
        }
        return str;
    }
}
