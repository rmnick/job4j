package ru.job4j.calculator.arithmetic;

public abstract class AbstractArithmeticOperation implements IArithmeticOperation {
    protected final String[] var;

    public AbstractArithmeticOperation(String[] var) {
        this.var = var;
    }

}
