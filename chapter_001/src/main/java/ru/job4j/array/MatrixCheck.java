package ru.job4j.array;

public class MatrixCheck {

    public boolean checkMatrix(boolean[][] arr) {
        boolean rsl = true;
        for (int i = 1; i < arr.length; i++) {
            rsl = arr[0][0] == arr[i][i] && arr[0][arr.length - 1] == arr[i][arr.length - i - 1];
            if (!rsl) {
                break;
            }
        }
        return rsl;
    }
}

