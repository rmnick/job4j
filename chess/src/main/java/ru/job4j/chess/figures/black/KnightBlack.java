package ru.job4j.chess.figures.black;

import ru.job4j.chess.WrongWayException;
import ru.job4j.chess.figures.BaseFigure;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class KnightBlack extends BaseFigure {
    public KnightBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws WrongWayException {
        if (!((calcDistance(source.x, dest.x) == 2 && calcDistance(source.y, dest.y) == 1)
                || (calcDistance(source.x, dest.x) == 1 && calcDistance(source.y, dest.y) == 2))) {
            throw new WrongWayException();
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightBlack(dest);
    }
}
