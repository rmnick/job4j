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
public class BishopBlack extends BaseFigure {

    public BishopBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws WrongWayException {
        if (!this.checkDiag(source, dest)) {
            throw new WrongWayException();
        }
        int length = calcDistance(source.x, dest.x);
        return move(source.x, source.y, Integer.compare(dest.x, source.x), Integer.compare(dest.y, source.y), length);
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
