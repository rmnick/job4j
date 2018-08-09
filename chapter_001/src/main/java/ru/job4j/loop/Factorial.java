package ru.job4j.loop;
/**
 * The factorial.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/10.
 */
public class Factorial {
    public int calc(int n) {
        int fact = 1;
        if (n == 0) {
            return fact;
        }
        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }
        return  fact;
    }
}
