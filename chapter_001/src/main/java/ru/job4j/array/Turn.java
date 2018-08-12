package ru.job4j.array;
/**
 * Turn.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/12.
 */
public class Turn {
    public static int[] turn(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[(array.length - 1) - i];
            array[(array.length - 1) - i] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
