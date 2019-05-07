package ru.job4j.gc;

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
        System.out.println(runtime.totalMemory() - runtime.freeMemory());
        User user = new User();
        System.out.println(runtime.totalMemory() - runtime.freeMemory());
//        System.gc();
//        info();
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
