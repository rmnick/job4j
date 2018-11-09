package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class Cell extends ReentrantLock {

    private final int positionX;

    private final int positionY;

    public Cell(final int positionX, final int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
