package ru.job4j.max;

/**
 * The operation of the maximum.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/07.
 */

public class Max {
    public int max(int first, int second) {
        return first >= second ? first : second;
    }
    public int max(int first, int second, int third) {
        return this.max((this.max(first, second)), third);
    }
}
