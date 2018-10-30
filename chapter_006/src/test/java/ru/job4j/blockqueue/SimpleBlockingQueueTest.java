package ru.job4j.blockqueue;

import org.junit.Test;

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
}
