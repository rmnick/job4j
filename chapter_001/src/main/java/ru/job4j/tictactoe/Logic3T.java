package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        return this.fillByX(0, 0, 1, 0)
                || this.fillByX(0, 1, 1, 0)
                || this.fillByX(0, 2, 1, 0)
                || this.fillByX(0, 0, 0, 1)
                || this.fillByX(1, 0, 0, 1)
                || this.fillByX(2, 0, 0, 1)
                || this.fillByX(0, 0, 1, 1)
                || this.fillByX(2, 0, -1, 1);
    }


    public boolean isWinnerO() {
        return this.fillByO(0, 0, 1, 0)
                || this.fillByO(0, 1, 1, 0)
                || this.fillByO(0, 2, 1, 0)
                || this.fillByO(0, 0, 0, 1)
                || this.fillByO(1, 0, 0, 1)
                || this.fillByO(2, 0, 0, 1)
                || this.fillByO(0, 0, 1, 1)
                || this.fillByO(2, 0, -1, 1);
    }

    public boolean hasGap() {
        boolean rsl = false;
        for (int i = 0; i < this.table.length; i++) {
            if (rsl) {
                break;
            }
            for (int j = 0; j < this.table.length; j++) {
                if (!(this.table[i][j].hasMarkX() || this.table[i][j].hasMarkO())) {
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    public boolean fillByX(int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!cell.hasMarkX()) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean fillByO(int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!cell.hasMarkO()) {
                result = false;
                break;
            }
        }
        return result;
    }


}
