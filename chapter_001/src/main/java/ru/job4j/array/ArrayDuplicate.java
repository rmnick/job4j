package ru.job4j.array;

import java.util.Arrays;

/**
 * Remove duplicates.
 * @author Rodionov Nick.
 * @version 1.0.
 * @since 2018/08/14.
 */

public class ArrayDuplicate {
    public String[] remove(String[] arr) {
        int flag = 0;
        for (int i = 0; i < arr.length - flag; i++) {
            for (int j = i + 1; j < arr.length - flag; j++) {
                if (arr[i].equals(arr[j])) {
                    while (j != arr.length - flag - 1
                            && arr[arr.length - flag - 1].equals(arr[j])) {
                        flag++;
                    }
                    String tmp = arr[arr.length - flag - 1];
                    arr[arr.length - flag - 1] = arr[j];
                    arr[j] = tmp;
                    flag++;
                }
            }
        }
        return Arrays.copyOf(arr, arr.length - flag);
    }
}
