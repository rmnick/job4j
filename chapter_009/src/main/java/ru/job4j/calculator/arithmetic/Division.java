package ru.job4j.calculator.arithmetic;

public class Division extends AbstractArithmeticOperation {

    public Division(String[] var) {
        super(var);
    }

    @Override
    public double calc() {
        return Double.valueOf(this.var[0]) / Double.valueOf(this.var[1]);
    }
}
