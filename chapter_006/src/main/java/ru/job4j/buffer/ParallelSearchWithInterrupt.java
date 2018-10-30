package ru.job4j.buffer;

import ru.job4j.blockqueue.SimpleBlockingQueue;

public class ParallelSearchWithInterrupt {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread producer = new Thread(
                () -> {
                    try {
                        for (int index = 0; index != 10; index++) {
                            queue.offer(index);
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            System.out.println("thread is over");
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.start();
        try {
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.interrupt();
    }
}
