package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.black.BishopBlack;
import ru.job4j.chess.figures.black.PawnBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {
    @Test
    public void logicWhenTestBishopThenTrue() {
        Logic logic = new Logic();
        Figure pawn = new PawnBlack(Cell.F5);
        Figure bishop = new BishopBlack(Cell.C8);
        logic.add(pawn);
        logic.add(bishop);
        assertThat(logic.move(bishop.position(), Cell.E6), is(true));
    }
    @Test
    public void logicWhenTestBishopThenFalse() {
        Logic logic = new Logic();
        Figure pawn = new PawnBlack(Cell.F5);
        Figure bishop = new BishopBlack(Cell.C8);
        logic.add(pawn);
        logic.add(bishop);
        assertThat(logic.move(bishop.position(), Cell.G4), is(false));
    }
}
