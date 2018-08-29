package ru.job4j.chess.figures.black;

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

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[Math.abs(source.x - dest.x)];
        if((Math.abs(source.x - dest.x)) == (Math.abs(source.y - dest.y))) {
            for (int i = 0; i < steps.length; i++) {
                     steps[i] = Cell.values()[8*(dest.x + i) + (dest.y + i)];
                }
            }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

   // boolean diagCheck(int incr, int dX, int dY, Cell source, Cell dest) {
     //   return ((dest.getX() + incr == source.getX() + dX + incr) && (dest.getY() == source.getY() + dY + incr));

   // }
}
