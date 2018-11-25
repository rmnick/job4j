package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class Cell extends ReentrantLock {

    private final int positionX;

    private final int positionY;

    public Cell(final int x, final int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public int getX() {
        return positionX;
    }

    public int getY() {
        return positionY;
    }

}
