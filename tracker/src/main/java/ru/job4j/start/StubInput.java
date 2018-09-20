package ru.job4j.start;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StubInput implements Input {
    private int position = 0;
    private final List<String> values;
    public StubInput(final List<String> values) {
      this.values = values;
    }

    @Override
    public String ask(String question) {
        return values.get(position++);
    }

    public int ask(String question, List<Integer> range) {
        return Integer.valueOf(this.ask(question));
    }
}
