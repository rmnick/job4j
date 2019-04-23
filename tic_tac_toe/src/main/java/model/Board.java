package model;

public class Board {
    public final Figure[][] board;

    public Board() {
        this.board = new Figure[3][3];
    }

    public Board(final int size) {
        this.board = new Figure[size][size];
    }

}
