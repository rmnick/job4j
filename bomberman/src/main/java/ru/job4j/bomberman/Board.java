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
        //lock next Cell
        if (dest.tryLock(500, TimeUnit.MILLISECONDS)) {
           result = true;
           source.unlock();
       } else {
           System.out.println("block");
       }
       return result;
    }
}
