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
                //инкремент(декремент) для движения в нужную сторону
                int dX = 1;
                int dY = -1;
                for (int index = 0; index < steps.length; index++) {
                    steps[index] = Cell.values()[8 * (source.x + dX) + (source.y + dY)];
                    dY--;
                    dX++;
                }
            }
            if ((dest.x < source.x) && (dest.y < source.y)) {
                int dX = -1;
                int dY = -1;
                for (int index = 0; index < steps.length; index++) {
                    steps[index] = Cell.values()[8 * (source.x + dX) + (source.y + dY)];
                    dY--;
                    dX--;
                }
            }
            if ((dest.x < source.x) && (dest.y > source.y)) {
                int dX = -1;
                int dY = 1;
                for (int index = 0; index < steps.length; index++) {
                    steps[index] = Cell.values()[8 * (source.x + dX) + (source.y + dY)];
                    dY++;
                    dX--;
                }
            }
            if ((dest.x > source.x) && (dest.y > source.y)) {
                int dX = 1;
                int dY = 1;
                for (int index = 0; index < steps.length; index++) {
                    steps[index] = Cell.values()[8 * (source.x + dX) + (source.y + dY)];
                    dY++;
                    dX++;
                }
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
