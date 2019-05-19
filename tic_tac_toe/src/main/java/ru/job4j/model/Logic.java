package ru.job4j.model;

import java.util.function.Predicate;

public class Logic {
    private final Board board;

    public Logic(final Board board) {
        this.board = board;
    }


    public boolean move(Figure figure, int x, int y) {
        boolean result = false;
        if (board.getBoard()[y][x] == null) {
            board.getBoard()[y][x] = figure;
            result = true;
        }
        return result;
    }

    public boolean isWinnerX() {
        return this.fillByX(Figure::getMark, 0, 0, 1, 0)
                || this.fillByX(Figure::getMark, 0, 1, 1, 0)
                || this.fillByX(Figure::getMark, 0, 2, 1, 0)
                || this.fillByX(Figure::getMark, 0, 0, 0, 1)
                || this.fillByX(Figure::getMark, 1, 0, 0, 1)
                || this.fillByX(Figure::getMark, 2, 0, 0, 1)
                || this.fillByX(Figure::getMark, 0, 0, 1, 1)
                || this.fillByX(Figure::getMark, 2, 0, -1, 1);
    }

    public boolean isWinnerO() {
        return this.fillByO(Figure::getMark, 0, 0, 1, 0)
                || this.fillByO(Figure::getMark, 0, 1, 1, 0)
                || this.fillByO(Figure::getMark, 0, 2, 1, 0)
                || this.fillByO(Figure::getMark, 0, 0, 0, 1)
                || this.fillByO(Figure::getMark, 1, 0, 0, 1)
                || this.fillByO(Figure::getMark, 2, 0, 0, 1)
                || this.fillByO(Figure::getMark, 0, 0, 1, 1)
                || this.fillByO(Figure::getMark, 2, 0, -1, 1);
    }

    public boolean hasGap() {
        boolean rsl = false;
        for (int i = 0; i < this.board.getBoard().length; i++) {
            if (rsl) {
                break;
            }
            for (int j = 0; j < this.board.getBoard()[i].length; j++) {
                if (this.board.getBoard()[i][j] == null) {
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    public boolean fillByX(Predicate<Figure> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = false;
        for (int index = 0; index != this.board.getBoard().length; index++) {
            Figure figure = this.board.getBoard()[startY][startX];
            startX += deltaX;
            startY += deltaY;
            if (figure != null && predicate.test(figure)) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean fillByO(Predicate<Figure> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = false;
        for (int index = 0; index != this.board.getBoard().length; index++) {
            Figure figure = this.board.getBoard()[startY][startX];
            startX += deltaX;
            startY += deltaY;
            if (figure != null && !predicate.test(figure)) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }
}
