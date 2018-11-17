package ru.job4j.synchronizers;

import org.junit.Test;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    @Test
    public void parkingTest() throws InterruptedException {
        boolean[] spots = new boolean[5];
        Semaphore smph = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Thread(new Car(i, smph, spots)).start();
            Thread.sleep(500);
        }

        Thread.sleep(10000);
    }
}
