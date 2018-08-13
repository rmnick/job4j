package ru.job4j.array;

public class Matrix {
    public int[][] multiply(int size) {
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = (i + 1) * (j + 1);
            }
        }
        return arr;
    }
}
