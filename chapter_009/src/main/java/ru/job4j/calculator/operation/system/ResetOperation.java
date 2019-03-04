package ru.job4j.calculator.operation.system;

import ru.job4j.calculator.*;

public class ResetOperation extends AbstractSystem {
    public ResetOperation(String name) {
        super(name);
    }

    @Override
    public String execute(String answer, IInput input, IOutput output, Validator validator) {
        return "0";
    }
}