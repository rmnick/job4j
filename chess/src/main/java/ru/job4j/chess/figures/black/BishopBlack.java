package ru.job4j.chess.figures.black;

import ru.job4j.chess.WrongWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;
    public boolean flag = false;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws WrongWayException {
        Cell[] steps;
        if (!((Math.abs(dest.x - source.x)) == (Math.abs(dest.y - source.y)))) {
            throw new WrongWayException("Cell is NULL");
        }
        int length = Math.abs(source.x - dest.x);
        steps = move(source.x, source.y, this.compare(dest.x, source.x), this.compare(dest.y, source.y), length);
        return steps;
    }

    private Cell[] move(int sourceX, int sourceY, int dX, int dY, int length) {
        Cell[] steps = new Cell[length];
        for (int index = 0; index < steps.length; index++) {
            sourceX += dX;
            sourceY += dY;
            steps[index] = Cell.values()[8 * sourceX + sourceY];
        }
        return steps;
    }

    private int compare(int x, int y) {
        int delt = 0;
        if (x > y) {
            delt = 1;
        }
        if (x < y) {
            delt = -1;
        }
        return  delt;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
