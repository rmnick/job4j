package ru.job4j.blockqueue;

/**
 * the class has been made for a test with two threads
 * the number of iteration in a method run() is less than the same value in Producer
 */

public class SingleConsumer extends Thread {
    private final SimpleBlockingQueue<Integer> queue;

    public SingleConsumer(final SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000000; i++) {
                this.queue.poll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
