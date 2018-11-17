package ru.job4j.synchronizers;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private int carNumber;
    private Semaphore smph;
    private boolean[] parkingSpot;

    public Car(int carNumber, Semaphore smph, boolean[] parkingSpot) {
        this.carNumber = carNumber;
        this.smph = smph;
        this.parkingSpot = parkingSpot;
    }

    @Override
    public void run() {
        try {
            smph.acquire();
            System.out.printf("car number - %d is looking for free spot\n", carNumber);
            for (int i = 0; i < parkingSpot.length; i++) {
                if (!parkingSpot[i]) {
                    parkingSpot[i] = true;
                    System.out.printf("car number - %d takes place %d\n", carNumber, i);
                    Thread.sleep(3000);
                    System.out.printf("car number -%d leaves parking\n", carNumber);
                    parkingSpot[i] = false;
                }
                smph.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
