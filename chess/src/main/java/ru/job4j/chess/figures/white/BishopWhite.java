package ru.job4j.chess.figures.white;

import ru.job4j.chess.WrongWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopWhite implements Figure {
    private final Cell position;

    public BishopWhite(final Cell position) {
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
            throw new WrongWayException();
        }
        int length = Math.abs(source.x - dest.x);
        steps = move(source.x, source.y, Integer.compare(dest.x, source.x), Integer.compare(dest.y, source.y), length);
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

    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
