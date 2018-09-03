package ru.job4j.chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.black.BishopBlack;
import ru.job4j.chess.figures.black.PawnBlack;

import static org.hamcrest.CoreMatchers.containsString;

public class LogicOtherTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void logicWhenTestBishopThenOccupiedException() {

        thrown.expect(OccupiedException.class);
        thrown.expectMessage(containsString("field is occupied"));

        Logic logic = new Logic();
        Figure pawn = new PawnBlack(Cell.B3);
        Figure bishop = new BishopBlack(Cell.F7);
        logic.add(pawn);
        logic.add(bishop);
        logic.move(bishop.position(), Cell.A2);
    }
    @Test
    public void logicWhenTestBishopThenWrongWayException() {
        thrown.expect(WrongWayException.class);
        thrown.expectMessage(containsString("wrong way"));
        Logic logic = new Logic();
        Figure bishop = new BishopBlack(Cell.F8);
        logic.add(bishop);
        logic.move(bishop.position(), Cell.D8);
    }
    @Test
    public void logicWhenTestBishopFigureNotFoundException() {
        thrown.expect(FigureNotFoundException.class);
        thrown.expectMessage(containsString("there's no figure"));
        Logic logic = new Logic();
        Figure pawnOne = new PawnBlack(Cell.D7);
        Figure pawnTwo = new PawnBlack(Cell.C7);
        Figure bishopOne = new BishopBlack(Cell.F8);
        Figure bishopTwo = new BishopBlack(Cell.C8);
        logic.add(pawnOne);
        logic.add(pawnTwo);
        logic.add(bishopOne);
        logic.add(bishopTwo);
        logic.move(Cell.A8, Cell.B7);
     }
}
