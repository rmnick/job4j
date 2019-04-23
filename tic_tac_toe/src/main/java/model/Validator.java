package model;

public class Validator {

    public int[] parseMove(String str) {
        int[] arr = new int[2];
        arr[0] = Integer.valueOf(str.substring(0, 1));
        arr[1] = Integer.valueOf(str.substring(1));
        return arr;
    }
}
