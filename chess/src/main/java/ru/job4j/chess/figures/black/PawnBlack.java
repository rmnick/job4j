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
public class PawnBlack extends BaseFigure {

    public PawnBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws WrongWayException {
        if (!(source.y == dest.y + 1 && source.x == dest.x)) {
            throw new WrongWayException();
        }
        return new Cell[]{dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
