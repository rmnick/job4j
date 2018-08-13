package ru.job4j.array;
/**
 * Check boolean expression.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/12.
 */
public class Check {
    public static boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 1; i < data.length; i++) {
            if (data[0] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
