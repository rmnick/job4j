package ru.job4j.model;

public class Board {
    private Figure[][] board;

    public Board() {
        this.board = new Figure[3][3];
    }

    public Board(final int size) {
        this.board = new Figure[size][size];
    }

    public void setSize(final  int size) {
        this.board = new Figure[size][size];
    }

    public Figure[][] getBoard() {
        return this.board;
    }

}
