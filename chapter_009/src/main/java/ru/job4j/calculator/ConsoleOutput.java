package ru.job4j.calculator;

public class ConsoleOutput implements IOutput {
    @Override
    public void out(final String str) {
        System.out.println(str);
    }
}
