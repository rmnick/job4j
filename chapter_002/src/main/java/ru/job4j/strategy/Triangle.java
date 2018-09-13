package ru.job4j.strategy;
/**
 * @author Rodionov Nick (mailto:r.m.nick@yandex.ru)
 * @version $Id$
 * @since 2018.08.25
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder str = new StringBuilder();
        str.append("      +");
        str.append(System.lineSeparator());
        str.append("    + +");
        str.append(System.lineSeparator());
        str.append("  + + +");
        str.append(System.lineSeparator());
        str.append("+ + + +");
        return str.toString();
    }
}
