package ru.job4j.threadpool;

import ru.job4j.blockqueue.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            threads.add(new ThreadInPool());
        }
        for (Thread threadInPool : threads) {
            threadInPool.start();
        }
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private class ThreadInPool extends Thread {
        @Override
        public void run() {
            while (!this.isInterrupted()) {
                try {
                    Runnable job = (Runnable) tasks.poll();
                    job.run();
                } catch (InterruptedException e) {
                    threadName.add(this.getName());
                    this.interrupt();
                }
            }
        }
    }

    /**
     * temporary blocks for tests
     */
    private final List<String> threadName = new ArrayList<>();
    public List<String> getThreadName() {
        return threadName;
    }
}