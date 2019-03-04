package ru.job4j.calculator.operation;

import ru.job4j.calculator.IInput;
import ru.job4j.calculator.IOutput;
import ru.job4j.calculator.Validator;

public interface IOperation {
    String execute(String answer, IInput input, IOutput output, Validator validator);
}
