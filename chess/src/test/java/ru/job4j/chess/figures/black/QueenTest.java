package ru.job4j.chess.figures.black;

import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueenTest {
    @Test
    public void queenTestDiagonal() {
        Figure queen = new QueenBlack(Cell.D8);

        Cell[] test = queen.way(Cell.D8, Cell.H4);
        assertThat(test[2], is(Cell.G5));
    }
    @Test
    public void queenTestHorizontal() {
        Figure queen = new QueenBlack(Cell.A5);

        Cell[] test = queen.way(Cell.A5, Cell.H5);
        assertThat(test[6], is(Cell.H5));
    }
    @Test
    public void queenTestVertical() {
        Figure queen = new QueenBlack(Cell.C7);

        Cell[] test = queen.way(Cell.C7, Cell.C1);
        assertThat(test[5], is(Cell.C1));
    }
}
