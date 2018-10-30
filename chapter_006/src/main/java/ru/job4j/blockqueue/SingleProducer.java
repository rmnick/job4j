package ru.job4j.blockqueue;

/**
 * the class has been made for a test with two threads
 * the number of iteration in a method run() is greater than the same value in Consumer
 */

public class SingleProducer extends Thread {
    private final SimpleBlockingQueue<Integer> queue;

    public SingleProducer(final SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000010; i++) {
                this.queue.offer(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
