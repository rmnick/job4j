package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;

public class Board {

    public final Cell[][] board;

    public final int size;

    public Board(final int size) {
        this.size = size;
        board = new Cell[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = new Cell(x, y);
            }
        }
    }


    public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = false;
        //lock starting position when we make our hero
        if (!source.isHeldByCurrentThread()) {
            source.lock();
        }
        int time = 500;
        Thread sourceThread = source.getThread();
        Thread destThread = dest.getThread();
        if (sourceThread.getName().contains("Monster")
                && !(destThread != null && destThread.getName().contains("Blocks"))) {
            time = 5000;
        }
        //lock next Cell
        if (dest.tryLock(time, TimeUnit.MILLISECONDS)) {
           result = true;
           source.unlock();
       } else {
           System.out.printf("%s is blocked\n", Thread.currentThread().getName());
       }
       return result;
    }
}
