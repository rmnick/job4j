package ru.job4j.chess.figures;

public abstract class BaseFigure implements Figure {
   private final Cell position;

    protected BaseFigure(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    public Cell[] move(int sourceX, int sourceY, int dX, int dY, int length) {
        Cell[] steps = new Cell[length];
        for (int index = 0; index < steps.length; index++) {
            sourceX += dX;
            sourceY += dY;
            steps[index] = Cell.values()[8 * sourceX + sourceY];
        }
        return steps;
    }

    public boolean checkDiag(Cell source, Cell dest) {
        return (Math.abs(dest.x - source.x) == Math.abs(dest.y - source.y));
    }

    public boolean checkHorizon(Cell source, Cell dest) {
        return (Math.abs(dest.y - source.y) == 0);
    }

    public boolean checkVertical(Cell source, Cell dest) {
        return (Math.abs(dest.x - source.x) == 0);
    }

}
