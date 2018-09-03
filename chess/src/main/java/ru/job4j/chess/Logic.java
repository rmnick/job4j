package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws WrongWayException, FigureNotFoundException, OccupiedException {
        boolean rst = false;
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("there's no figure");
        }
        Cell[] steps;
        steps = this.figures[index].way(source, dest);
        if (this.occupied(steps)) {
            throw new OccupiedException("field is occupied");
        }
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
            rst = true;
            this.figures[index] = this.figures[index].copy(dest);
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    private boolean occupied(Cell[] steps) {
        boolean flag = false;
        for (int i = 0; i < steps.length; i++) {
            if (flag) {
                break;
            }
            for (int j = 0; j < figures.length; j++) {
                if (figures[j] != null && steps[i].equals(figures[j].position())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}