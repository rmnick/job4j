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
        System.out.println("in Logic X winner!!!");
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
        System.out.println("in Logic O winner!!!");
        return !this.fillByO(Figure::getMark, 0, 0, 1, 0)
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
        for (int i = 0; i < this.board.board.length; i++) {
            if (rsl) {
                break;
            }
            for (int j = 0; j < this.board.board[i].length; j++) {
                if (this.board.board[i][j] == null) {
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    public boolean fillByX(Predicate<Figure> predicate, int startX, int startY, int deltaX, int deltaY) {
        System.out.println("in Logic fillByX");
        boolean result = false;
        for (int index = 0; index != this.board.board.length; index++) {
            System.out.println(board.board.length);
            Figure figure = this.board.board[startX][startY];
            startX += deltaX;
            startY += deltaY;
            System.out.println(startX + " " + startY + " " + (figure != null ? figure.getMark() : null));
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
        System.out.println("in Logic fillByO");
        boolean result = false;
        for (int index = 0; index != this.board.board.length; index++) {
            Figure figure = this.board.board[startX][startY];
            startX += deltaX;
            startY += deltaY;
            System.out.println(startX + " " + startY + " " + (figure != null ? figure.getMark() : null));
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
