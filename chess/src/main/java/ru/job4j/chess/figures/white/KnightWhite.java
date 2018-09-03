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
public class KnightWhite implements Figure {
    private final Cell position;

    public KnightWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws WrongWayException {
        if (!((Math.abs(dest.x - source.x) == 2 && Math.abs(dest.y - source.y) == 1)
                || ((Math.abs(dest.x - source.x)) == 1 && Math.abs(dest.y - source.y) == 2))) {
            throw new WrongWayException();
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
