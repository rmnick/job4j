package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        //horizontal check
        boolean rsl = false;
        for (int i = 0; i < this.table.length; i++) {
            if (rsl) {
                return true;
            }
            for (int j = 0; j < this.table.length; j++) {
                if (this.table[i][j].hasMarkX()) {
                    rsl = true;
                } else {
                    rsl = false;
                    break;
                }
            }
        }
        if (rsl) {
            return  true;
        }
        //vertical check
        for (int i = 0; i < this.table.length; i++) {
            if (rsl) {
                return  true;
            }
            for (int j = 0; j < this.table.length; j++) {
                if (!this.table[j][i].hasMarkX()) {
                    rsl = false;
                    break;
                } else {
                    rsl = true;
                }
            }
        }
        if (rsl) {
            return  true;
        }
        //diag check one
        for (int i = 0; i < this.table.length; i++) {
            if (!this.table[i][i].hasMarkX()) {
                rsl = false;
                break;
            } else {
                rsl = true;
            }
        }
        if (rsl) {
            return true;
        }

        //diag check two
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i][this.table.length - i - 1].hasMarkX()) {
                rsl = true;
            } else {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    public boolean isWinnerO() {
        //horizont check
        boolean rsl = false;
        for (int i = 0; i < this.table.length; i++) {
            if (rsl) {
                return true;
            }
            for (int j = 0; j < this.table.length; j++) {
                if (this.table[i][j].hasMarkO()) {
                    rsl = true;
                } else {
                    rsl = false;
                    break;
                }
            }
        }
        if (rsl) {
            return  true;
        }
        //vertical check
        for (int i = 0; i < this.table.length; i++) {
            if (rsl) {
                return true;
            }
            for (int j = 0; j < this.table.length; j++) {
                if (this.table[j][i].hasMarkO()) {
                    rsl = true;
                } else {
                    rsl = false;
                    break;
                }
            }
        }
        if (rsl) {
            return  true;
        }
        //diag check one
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i][i].hasMarkO()) {
                rsl = true;
            } else {
                rsl = false;
                break;
            }
        }
        if (rsl) {
            return true;
        }
        //diag check two
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i][this.table.length - i - 1].hasMarkO()) {
                rsl = true;
            } else {
                rsl = false;
                break;
            }
        }
        return rsl;
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
                } else {
                    rsl = false;
                }
            }
        }
        return rsl;
    }

}
