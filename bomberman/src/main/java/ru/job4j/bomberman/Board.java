package ru.job4j.bomberman;

public class Board {

    private final Cell[][] board;

    public Board(final int size) {
        board = new Cell[size][];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = new Cell(x, y);
            }
        }
    }
}
