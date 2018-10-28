package ru.job4j.algorithms;

public class Sort {

    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                 temp = arr[j];
                 arr[j] = arr[j + 1];
                 arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectedSort(int[] arr) {
        int temp = arr[0];
        int index = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            if (flag) {
                arr[index] = arr[i - 1];
                arr[i - 1] = temp;
                flag = false;
            }
            temp = arr[i];
            for (int j = i; j < arr.length - 1; j++) {
                if (arr[j + 1] < temp) {
                    temp = arr[j + 1];
                    index = j + 1;
                    flag = true;
                }
            }
        }
    }

    public static void insertedSort(int[] arr) {
        int temp;
        int j;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            j = i;
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
                arr[j] = temp;
            }
        }
    }
}
