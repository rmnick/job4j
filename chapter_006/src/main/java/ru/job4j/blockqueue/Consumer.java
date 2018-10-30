package ru.job4j.blockqueue;

public class Consumer<E> extends Thread {
    private final SimpleBlockingQueue<E> queue;

    public Consumer(final SimpleBlockingQueue<E> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            this.queue.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
