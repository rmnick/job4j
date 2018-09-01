package ru.job4j.chess.figures.black;
import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KingTest {
    @Test
    public void kingTestVertical() {
        Figure king = new KingBlack(Cell.E8);

        Cell[] test = king.way(Cell.E8, Cell.E7);
        assertThat(test[0], is(Cell.E7));
    }
    @Test
    public void kingTestHor() {
        Figure king = new KingBlack(Cell.E8);

        Cell[] test = king.way(Cell.E8, Cell.F8);
        assertThat(test[0], is(Cell.F8));
    }
    @Test
    public void kingTestDiag() {
        Figure king = new KingBlack(Cell.E8);

        Cell[] test = king.way(Cell.E8, Cell.D7);
        assertThat(test[0], is(Cell.D7));
    }
}
