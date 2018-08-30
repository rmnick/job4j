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
    public boolean flag = false;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        //кол-во шагов = кол-во cell
        Cell[] steps = new Cell[Math.abs(dest.x - source.x)];
        //проверка на движение по диагонали
        if ((Math.abs(dest.x - source.x)) == (Math.abs(dest.y - source.y))) {
            //условие для движения вправо вверх
            if ((dest.x > source.x) && (dest.y < source.y)) {
               steps = move(source.x, source.y, 1, -1, steps.length);
            }
            //условие для движения влево вверх
            if ((dest.x < source.x) && (dest.y < source.y)) {
                steps = move(source.x, source.y, -1, -1, steps.length);
            }
            //условие для движения влево вниз
            if ((dest.x < source.x) && (dest.y > source.y)) {
                steps = move(source.x, source.y, -1, 1, steps.length);

            }
            //условие для движения вправо вниз
            if ((dest.x > source.x) && (dest.y > source.y)) {
                steps = move(source.x, source.y, 1, 1, steps.length);
            }
        }
        return steps;
    }

    private Cell[] move(int sourceX, int sourceY, int dX, int dY, int length) {
        Cell[] steps = new Cell[Math.abs(length)];
        for (int index = 0; index < steps.length; index++) {
            sourceX += dX;
            sourceY += dY;
            steps[index] = Cell.values()[8 * sourceX + sourceY];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
