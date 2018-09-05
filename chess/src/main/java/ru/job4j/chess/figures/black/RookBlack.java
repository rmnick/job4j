package ru.job4j.chess.figures.black;

import ru.job4j.chess.WrongWayException;
import ru.job4j.chess.figures.BaseFigure;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Petr Arsentev, Nick Rodionov (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookBlack extends BaseFigure {

    public RookBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws WrongWayException {
        if (!((checkHorizon(source, dest)) || (checkVertical(source, dest)))) {
            throw new WrongWayException();
        }
        int length;
        if (checkVertical(source, dest)) {
            length = calcDistance(source.y, dest.y);
        } else {
            length = calcDistance(source.x, dest.x);
        }
        return move(source.x, source.y, Integer.compare(dest.x, source.x), Integer.compare(dest.y, source.y), length);
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}
