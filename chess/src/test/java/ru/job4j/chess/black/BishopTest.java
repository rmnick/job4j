package ru.job4j.chess.black;

import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.black.BishopBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopTest {
    @Test
    public void bishopTest() {
        Logic l = new Logic();
        Figure bish = new BishopBlack(Cell.A8);

        Cell[] test = bish.way(Cell.A8, Cell.H1);
        assertThat(test[5], is(Cell.G2));
    }
}
