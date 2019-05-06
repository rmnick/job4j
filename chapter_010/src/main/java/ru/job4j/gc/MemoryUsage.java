package ru.job4j.gc;

public class MemoryUsage {

    public static class User {

    }
    public static void main(String[] args) {
        User user = new User();
        info();
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
}
