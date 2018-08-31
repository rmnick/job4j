package ru.job4j.chess.figures;

public abstract class AbstractFigure implements Figure {
    private final Cell position;

    public AbstractFigure(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[]{dest};
    }

    @Override
    public abstract Figure copy(Cell dest);
}
