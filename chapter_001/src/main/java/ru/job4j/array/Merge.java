package ru.job4j.array;

public class Merge {

    public int[] mergeAB(int[] arrA, int[] arrB) {
        int[] result = new int[arrA.length + arrB.length];
        int step = 0;
        int i = 0, j = 0;
        for (; i < arrA.length && j < arrB.length; step++) {
            if (arrA[i] < arrB[j]) {
                result[step] = arrA[i];
                i++;
            } else {
                result[step] = arrB[j];
                j++;
            }
        }
        for (; i < arrA.length; i++, step++) {
            result[step] = arrA[i];
        }
        for (; j < arrB.length; j++, step++) {
            result[step] = arrB[j];
        }
        return result;
    }

}
