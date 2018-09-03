package ru.job4j.chess.figures.black;

import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopTest {
    @Test
    public void bishopTestDiagonalUp() {
        Figure bishop = new BishopBlack(Cell.A8);

        Cell[] test = bishop.way(Cell.A8, Cell.H1);
        assertThat(test[5], is(Cell.G2));
    }
    @Test
    public void bishopTestDiagonalDown() {
        Figure bishop = new BishopBlack(Cell.F5);

        Cell[] test = bishop.way(Cell.F5, Cell.C8);
        assertThat(test[2], is(Cell.C8));
    }
    @Test
    public void bishopTestMoveThenRightArrayCells() {
        Figure bishop = new BishopBlack(Cell.A8);
        Cell[] test = bishop.way(Cell.A8, Cell.H1);
        Cell[] example = new Cell[] {Cell.B7, Cell.C6, Cell.D5, Cell.E4, Cell.F3, Cell.G2, Cell.H1};
        assertThat(test, is(example));
    }
}
