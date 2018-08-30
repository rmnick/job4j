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
        Figure bish = new BishopBlack(Cell.B5);

        Cell[] test = bish.way(Cell.B5, Cell.F1);
        assertThat(test[1], is(Cell.D3));
    }
}
