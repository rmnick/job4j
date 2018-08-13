package ru.job4j.array;

public class MatrixCheck {

    public boolean checkMatrix(boolean[][] arr) {
        boolean rsl = true;
        if (arr.length % 2 != 0) { //checking for independent diagonals
            rsl = (arr[0][0] == arr[0][arr.length - 1]) && this.checkLeft(arr); //if first elements are different then false without checking, if not then checking only one diag.
        } else {
            rsl = this.checkLeft(arr) && this.checkRight(arr);
        }
        return  rsl;
    }
    private boolean checkLeft(boolean[][] arr) {
        boolean rsl = true;
        for (int i = 1; i < arr.length; i++) {
            rsl = arr[0][0] == arr[i][i];
            if (!rsl) {
                break;
            }
        }
        return rsl;
    }

    private boolean checkRight(boolean[][] arr) {
        boolean rsl = true;
        for (int i = 1; i < arr.length; i++) {
            rsl = arr[0][arr.length - 1] == arr[i][arr.length - i - 1];
            if (!rsl) {
                break;
            }
        }
        return rsl;
    }
}
