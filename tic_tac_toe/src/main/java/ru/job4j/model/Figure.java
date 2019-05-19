package ru.job4j.model;

public class Figure {
    public final boolean mark;

    public Figure(final boolean mark) {
        this.mark = mark;
    }

    public boolean getMark() {
        return this.mark;
    }

    @Override
    public String toString() {
        return mark ? "x" : "o";
    }
}
