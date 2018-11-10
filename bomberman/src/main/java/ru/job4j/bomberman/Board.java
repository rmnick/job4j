package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;

public class Board {

    private final Cell[][] board;


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

    public boolean move(Cell source, Cell dest) {
        boolean result = false;
        try {
            if (dest.tryLock(500, TimeUnit.MILLISECONDS)) {
                result = true;
                source.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * for tests
     * @return array[][] of Cell.
     */
    public Cell[][] getBoard() {
        return this.board;
    }
}
