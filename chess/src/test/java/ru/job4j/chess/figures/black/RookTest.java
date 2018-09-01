package ru.job4j.chess.figures.black;

import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RookTest {
    @Test
    public void rookTestHorizontal() {
        Figure rook = new RookBlack(Cell.A5);

        Cell[] test = rook.way(Cell.A5, Cell.H5);
        assertThat(test[6], is(Cell.H5));
    }
    @Test
    public void rookTestVertical() {
        Figure rook = new RookBlack(Cell.C7);

        Cell[] test = rook.way(Cell.C7, Cell.C1);
        assertThat(test[5], is(Cell.C1));
    }
}
