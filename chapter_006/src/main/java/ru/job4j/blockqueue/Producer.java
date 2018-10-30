package ru.job4j.blockqueue;

public class Producer<E> extends Thread {

    private final SimpleBlockingQueue<E> queue;
    private final E value;

    public Producer(final SimpleBlockingQueue<E> queue, final E value) {
        this.queue = queue;
        this.value = value;
    }

    @Override
    public void run() {
        try {
            this.queue.offer(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
