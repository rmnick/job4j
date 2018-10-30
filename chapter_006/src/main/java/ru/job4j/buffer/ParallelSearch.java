package ru.job4j.buffer;

import ru.job4j.blockqueue.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final int poisonPill = -100;
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        queue.offer(poisonPill);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        );
        final Thread consumer = new Thread(
                () -> {
                    int element;
                    while (true) {
                        try {
                            element = queue.poll();
                            if (element == poisonPill) {
                                break;
                            }
                            System.out.println(element);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.start();
    }
}
