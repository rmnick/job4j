package ru.job4j.start;

import java.util.List;

/**
 * @author Nick Rodionov
 * @since 2018.08.23
 */
public interface Input {
    String ask(String question);
    int ask(String question, List<Integer> range);
}
