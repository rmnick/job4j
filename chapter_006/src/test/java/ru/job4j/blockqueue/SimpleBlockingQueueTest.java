package ru.job4j.blockqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    /**
     * test between two threads.
     * The number of iteration in Producer is greater then the same value in Consumer.
     * difference(if it's less than MAX_SIZE) between numbers of iterations equals a new size of queue.
     */
    @Test
    public void whenThereIsDifferenceBetweenNumbersOfIterationsInThreadsThenSizeEqualsNumber() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new SingleProducer(queue);
        Thread consumer = new SingleConsumer(queue);
        consumer.start();
        producer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.getSize() == 10, is(true));
    }

    /**
     * testing multithreading operation with SimpleBlockingQueue
     */
    @Test
    public void multithreadingTest() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        int countProducer = 100000;
        int countConsumer = 99999;
        while (countProducer != 0) {
            new Producer<>(queue, countProducer).start();
            if (countConsumer != 0) {
                new Consumer<>(queue).start();

            }
            countProducer--;
            countConsumer--;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.getSize() == 1, is(true));
    }
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 100000; i++) {
                            queue.offer(i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                            System.out.println("interrupt in consumer");
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        List<Integer> list = new ArrayList<>(100000);
        IntStream.range(0, 100000).forEach(list::add);
        assertThat(buffer, is(list));
    }
}
