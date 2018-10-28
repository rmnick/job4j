package ru.job4j.problems;
public class CustomThread extends Thread {
    private Counter counter;
    public CustomThread(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increase();
        }
    }
    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 200; i++) {
            new CustomThread(counter).start();
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.count);
    }
}
class Counter {
    public int count = 0;
    public void increase() {
        count++;
    }
}
