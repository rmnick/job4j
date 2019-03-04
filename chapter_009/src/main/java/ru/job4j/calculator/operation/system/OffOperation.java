package ru.job4j.calculator.operation.system;

import ru.job4j.calculator.IInput;
import ru.job4j.calculator.IOutput;
import ru.job4j.calculator.Start;
import ru.job4j.calculator.Validator;

public class OffOperation extends AbstractSystem {
    public OffOperation(String name) {
        super(name);
    }

    @Override
    public String execute(String answer, IInput input, IOutput output, Validator validator) {
        return Start.OFF;
    }
}
