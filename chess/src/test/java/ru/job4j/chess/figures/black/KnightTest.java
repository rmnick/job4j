package ru.job4j.chess.figures.black;
import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KnightTest {
    @Test
    public void knightOne() {
        Figure knight = new KnightBlack(Cell.G8);

        Cell[] test = knight.way(Cell.G8, Cell.H6);
        assertThat(test[0], is(Cell.H6));
    }
    @Test
    public void knightTwo() {
        Figure knight = new KnightBlack(Cell.B8);

        Cell[] test = knight.way(Cell.B8, Cell.D7);
        assertThat(test[0], is(Cell.D7));
    }
}
