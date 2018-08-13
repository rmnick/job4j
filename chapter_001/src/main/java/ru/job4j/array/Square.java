package ru.job4j.array;
/**
 * Square.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/11.
 */
public class Square {
    public int[] calculate(int bound) {
        int[] arr = new int[bound];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.pow(i + 1, 2);
        }
        return arr;
    }
}
