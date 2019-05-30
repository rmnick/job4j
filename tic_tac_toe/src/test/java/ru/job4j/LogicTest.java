package ru.job4j;

import org.junit.Test;
import ru.job4j.model.Board;
import ru.job4j.model.Figure;
import ru.job4j.model.Logic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LogicTest {
    @Test
    public void whenThreeXInHorizonLineThenWinnerX() {
        Board board = new Board();
        Logic logic = new Logic(board);
        for (int j = 0; j < board.getBoard().length; j++) {
            for (int i = 0; i < board.getBoard().length; i++) {
                board.getBoard()[j][i] = new Figure(true);
            }
            assertThat(logic.isWinnerX(), is(true));
        }
    }

    @Test
    public void whenThreeOInHorizonLineThenWinnerO() {
        Board board = new Board();
        Logic logic = new Logic(board);
        for (int j = 0; j < board.getBoard().length; j++) {
            for (int i = 0; i < board.getBoard().length; i++) {
                board.getBoard()[j][i] = new Figure(false);
            }
            assertThat(logic.isWinnerO(), is(true));
        }
    }

    @Test
    public void whenThreeXInVerticalLineThenWinnerX() {
        Board board = new Board();
        Logic logic = new Logic(board);
        for (int j = 0; j < board.getBoard().length; j++) {
            for (int i = 0; i < board.getBoard().length; i++) {
                board.getBoard()[i][j] = new Figure(true);
            }
            assertThat(logic.isWinnerX(), is(true));
        }
    }

    @Test
    public void whenThreeOInVerticalLineThenWinnerO() {
        Board board = new Board();
        Logic logic = new Logic(board);
        for (int j = 0; j < board.getBoard().length; j++) {
            for (int i = 0; i < board.getBoard().length; i++) {
                board.getBoard()[i][j] = new Figure(false);
            }
            assertThat(logic.isWinnerO(), is(true));
        }
    }

    @Test
    public void whenThreeXInDiagLineOneThenWinnerX() {
        Board board = new Board();
        Logic logic = new Logic(board);
        for (int j = 0, i = 0; j < board.getBoard().length; j++, i++) {
            board.getBoard()[i][j] = new Figure(true);
        }
        assertThat(logic.isWinnerX(), is(true));
    }

    @Test
    public void whenThreeOInDiagLineOneThenWinnerO() {
        Board board = new Board();
        Logic logic = new Logic(board);
        for (int j = 0, i = 0; j < board.getBoard().length; j++, i++) {
            board.getBoard()[j][i] = new Figure(false);
        }
        assertThat(logic.isWinnerO(), is(true));
    }

    @Test
    public void whenThreeXInDiagLineTwoThenWinnerX() {
        Board board = new Board();
        Logic logic = new Logic(board);
        for (int j = board.getBoard().length - 1, i = board.getBoard().length - 1; j >= 0; j--, i--) {
            board.getBoard()[i][j] = new Figure(true);
        }
        assertThat(logic.isWinnerX(), is(true));
    }

    @Test
    public void whenThreeOInDiagLineTwoThenWinnerO() {
        Board board = new Board();
        Logic logic = new Logic(board);
        for (int j = board.getBoard().length - 1, i = board.getBoard().length - 1; j >= 0; j--, i--) {
            board.getBoard()[j][i] = new Figure(false);
        }
        assertThat(logic.isWinnerO(), is(true));
    }

    @Test
    public void whenBoardHasEmptySpotThenHasGapIsTrue() {
        Board board = new Board();
        Logic logic = new Logic(board);
        assertThat(logic.hasGap(), is(true));
    }
}
