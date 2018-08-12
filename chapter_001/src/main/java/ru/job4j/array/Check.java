package ru.job4j.array;
/**
 * Check boolean expression.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/12.
 */
public class Check {
    public static boolean mono(boolean[] data) {
        boolean result = false;
        for (int i = 1; i < data.length; i++) {
            result = (data[i] == data[i - 1]);
            if (!result) {
                break;
            }
        }
        return result;
    }
}
