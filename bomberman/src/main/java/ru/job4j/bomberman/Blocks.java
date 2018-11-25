package ru.job4j.bomberman;

public class Blocks implements Runnable {

    private Cell[] cells;

    public Blocks(Cell[] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            for (Cell cell : cells) {
                cell.lock();
            }
            Thread.yield();
        }
    }
}
