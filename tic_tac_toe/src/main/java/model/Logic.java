package model;

import java.util.function.Predicate;

public class Logic {
    private final Board board;

    public Logic(final Board board) {
        this.board = board;
    }


    public boolean move(Figure figure, int x, int y) {
        boolean result = false;
        if (board.board[y][x] == null) {
            board.board[y][x] = figure;
            result = true;
        }
        return result;
    }

    public boolean isWinnerX() {
        return this.fillBy(Figure::getMark, 0, 0, 1, 0)
                || this.fillBy(Figure::getMark, 0, 1, 1, 0)
                || this.fillBy(Figure::getMark, 0, 2, 1, 0)
                || this.fillBy(Figure::getMark, 0, 0, 0, 1)
                || this.fillBy(Figure::getMark, 1, 0, 0, 1)
                || this.fillBy(Figure::getMark, 2, 0, 0, 1)
                || this.fillBy(Figure::getMark, 0, 0, 1, 1)
                || this.fillBy(Figure::getMark, 2, 0, -1, 1);
    }

    public boolean isWinnerO() {
        return !this.fillBy(Figure::getMark, 0, 0, 1, 0)
                || this.fillBy(Figure::getMark, 0, 1, 1, 0)
                || this.fillBy(Figure::getMark, 0, 2, 1, 0)
                || this.fillBy(Figure::getMark, 0, 0, 0, 1)
                || this.fillBy(Figure::getMark, 1, 0, 0, 1)
                || this.fillBy(Figure::getMark, 2, 0, 0, 1)
                || this.fillBy(Figure::getMark, 0, 0, 1, 1)
                || this.fillBy(Figure::getMark, 2, 0, -1, 1);
    }

    public boolean hasGap() {
        boolean rsl = false;
        for (int i = 0; i < this.board.board.length; i++) {
            if (rsl) {
                break;
            }
            for (int j = 0; j < this.board.board.length; j++) {
                if (this.board.board[i][j] != null) {
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    public boolean fillBy(Predicate<Figure> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.board.board.length; index++) {
            Figure figure = this.board.board[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (figure != null && !predicate.test(figure)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
