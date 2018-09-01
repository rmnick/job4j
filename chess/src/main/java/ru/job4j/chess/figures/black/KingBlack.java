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
public class KingBlack implements Figure {
    private final Cell position;

    public KingBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws WrongWayException {
        Cell[] steps;
        if (!(((Math.abs(dest.x - source.x) == 1) && (Math.abs(dest.y - source.y) == 1))
                || ((Math.abs(dest.x - source.x) == 0) && (Math.abs(dest.y - source.y) == 1))
                || ((Math.abs(dest.x - source.x) == 1) && (Math.abs(dest.y - source.y) == 0)))) {
            throw new WrongWayException("Cell is NULL");
        }
        steps = new Cell[] {dest};
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }
}
