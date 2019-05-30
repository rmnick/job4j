package ru.job4j.test;

import ru.job4j.controller.IInput;

import java.util.List;

/**
 * class for test application
 */
public class TestInput implements IInput {
    private List<String> list;
    private int index = 0;

    public TestInput(final List<String> list) {
        this.list = list;
    }

    @Override
    public String input() {
        return this.list.get(index++);
    }
}
