package ru.job4j.array;
/**
 * Check prefix.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/12.
 */

public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }
    /**
     * Checking that word starts with prefix.
     * @param prefix prefix.
     * @return true if it's true
     */
    public boolean startWith(String prefix) {
        char[] temp = prefix.toCharArray();
        boolean rslt = true;
        for (int i = 0; i < temp.length; i++) {
            rslt = temp[i] == this.data[i];
            if (!rslt) {
                break;
            }
        }
        return rslt;
    }
}
