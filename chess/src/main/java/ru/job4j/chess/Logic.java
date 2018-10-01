package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev, Nick Rodionov (parsentev@yandex.ru)
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
        int index = this.findBy((cell) -> cell.equals(source));
        if (index == -1) {
            throw new FigureNotFoundException("there's no figure");
        }
        Cell[] steps = this.figures[index].way(source, dest);
        if (this.occupied(steps)) {
            throw new OccupiedException("field is occupied");
        }
        this.figures[index] = this.figures[index].copy(dest);
        return true;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Predicate<Cell> predicate) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (figures[index] != null && predicate.test(this.figures[index].position())) {
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