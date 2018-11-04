package ru.job4j.blockqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<E> {
    @GuardedBy("this")
    private final Queue<E> queue = new LinkedList<>();
    private static final int MAX_SIZE = 10;
    private int size = 0;

    public void offer(E value) throws InterruptedException {
        synchronized (this) {
            while (size == MAX_SIZE) {
                wait();
            }
            if (size == 0) {
                notifyAll();
            }
            this.queue.offer(value);
            size++;
        }
    }

    public E poll() throws InterruptedException {
        synchronized (this) {
            E element;
            while (size == 0) {
                wait();
            }
            if (size == MAX_SIZE) {
                notifyAll();
            }
            element = this.queue.poll();
            size--;
            return element;
        }
    }

    /**
     * methods for test
     * @return int
     */
    public synchronized int getSize() {
        return this.size;
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
