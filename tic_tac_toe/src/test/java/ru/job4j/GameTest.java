package ru.job4j;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Board;
import ru.job4j.model.Figure;
import ru.job4j.model.Logic;
import ru.job4j.view.ConsoleOut;
import ru.job4j.view.IOut;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GameTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenNobodyIsWinThenDraw() {
        Board board = new Board();
        Logic logic = new Logic(board);
        IOut consoleOut = new ConsoleOut();
        Game game = new Game(logic);
        boolean mark = true;
        for (int j = 0; j < board.getBoard().length; j++) {
            for (int i = 0; i < board.getBoard().length; i++) {
                board.getBoard()[j][i] = new Figure(mark);
                mark = !mark;
            }
            if (j != board.getBoard().length - 1) {
                mark = !mark;
            }
        }
        assertThat(game.checkDrawState(consoleOut), is(true));
        assertThat(new String(out.toByteArray()), is("DRAW" + System.lineSeparator()));
    }

    @Test
    public void whenThreeXInLineThenWinnerX() {
        Board board = new Board();
        Logic logic = new Logic(board);
        IOut consoleOut = new ConsoleOut();
        Game game = new Game(logic);
        for (int j = 0; j < board.getBoard().length; j++) {
                board.getBoard()[j][0] = new Figure(true);
            }
        assertThat(game.checkWinnerState(consoleOut), is(true));
        assertThat(new String(out.toByteArray()), is("X is winner" + System.lineSeparator()));
    }

    @Test
    public void whenThreeOInLineThenWinnerO() {
        Board board = new Board();
        Logic logic = new Logic(board);
        IOut consoleOut = new ConsoleOut();
        Game game = new Game(logic);
        for (int j = 0; j < board.getBoard().length; j++) {
            board.getBoard()[j][0] = new Figure(false);
        }
        assertThat(game.checkWinnerState(consoleOut), is(true));
        assertThat(new String(out.toByteArray()), is("O is winner" + System.lineSeparator()));
    }
}
