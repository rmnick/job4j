package ru.job4j.loop;
/**
 * Calculation the summ of even numbers in a certain range .
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/10.
 */

public class Counter {
    public int add(int start, int finish) {
        int summ = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                summ = summ + i;
            }
        }
        return summ;
    }
}
