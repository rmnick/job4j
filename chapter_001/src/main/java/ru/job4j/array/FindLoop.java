package ru.job4j.array;
/**
 * Find index of element.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/12.
 */
public class FindLoop {
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }
        return rst;
    }
}
