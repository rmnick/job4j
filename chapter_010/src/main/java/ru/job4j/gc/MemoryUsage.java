package ru.job4j.gc;

import java.util.Arrays;

public class MemoryUsage {
    public static Runtime runtime = Runtime.getRuntime();

    public static class User {

        @Override
        public void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }

    }
    public static void main(String[] args) {
//        info();
//        for (int i = 0; i < 1500; i++) {
//            new User();
//            System.out.println(i);
//        }
//        System.out.println(runtime.totalMemory() - runtime.freeMemory());
//        User user = new User();
//        System.out.println(runtime.totalMemory() - runtime.freeMemory());
//        System.gc();
//        info();
        sort(getArr(200000000));
    }

    public static void info() {
        Runtime runtime = Runtime.getRuntime();

        //total memory
        long memTotal = runtime.totalMemory();
        System.out.println(String.format("total memory%s%d", System.lineSeparator(), memTotal));

        //free memory
        long memFree = runtime.freeMemory();
        System.out.println(String.format("free memory%s%d", System.lineSeparator(), memFree));

        //max memory
        long memMax = runtime.maxMemory();
        System.out.println(String.format("max memory%s%d", System.lineSeparator(), memMax));

        //used memory
        long memUsed = memTotal - memFree;
        System.out.println(String.format("used memory%s%d", System.lineSeparator(), memUsed));
    }

    public static int[] sort(int[] arr) {
        int step = 1;
        if (arr.length < 2) {
            return arr;
        } else {
            int[] buffOne = sort(Arrays.copyOfRange(arr, 0, arr.length / 2));
            int[] buffTwo = sort(Arrays.copyOfRange(arr, arr.length / 2, arr.length));
            return merge(buffOne, buffTwo);
        }
    }

    public static int[] merge(int[] arrOne, int[] arrTwo) {
        int[] result = new int[arrOne.length + arrTwo.length];
        int indexOne = 0, indexTwo = 0, indexResult = 0;
        while (indexOne < arrOne.length && indexTwo < arrTwo.length) {
            if (arrOne[indexOne] < arrTwo[indexTwo]) {
                result[indexResult++] = arrOne[indexOne++];
            } else {
                result[indexResult++] = arrTwo[indexTwo++];
            }
        }
        while (indexOne < arrOne.length) {
            result[indexResult++] = arrOne[indexOne++];
        }
        while (indexTwo < arrTwo.length) {
            result[indexResult++] = arrTwo[indexTwo++];
        }
        return result;
    }

    public static int[] getArr(final int number) {
        int[] arr = new int[number];
        for (int i = 0; i < number; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    public static void showArr(final int[] arr) {
        for (int num : arr) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }
}
