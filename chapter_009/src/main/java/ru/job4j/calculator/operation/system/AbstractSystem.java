package ru.job4j.calculator.operation.system;

import ru.job4j.calculator.IInput;
import ru.job4j.calculator.IOutput;
import ru.job4j.calculator.Validator;
import ru.job4j.calculator.operation.ICalculation;
import ru.job4j.calculator.operation.IOperation;

public abstract class AbstractSystem implements IOperation {
    protected final String name;


    public AbstractSystem(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
