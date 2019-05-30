package ru.job4j;

import org.junit.Test;
import ru.job4j.controller.ConsoleInput;
import ru.job4j.controller.IInput;
import ru.job4j.model.Board;
import ru.job4j.model.Validator;
import ru.job4j.view.ConsoleOut;
import ru.job4j.view.IOut;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidatorTest {
    @Test
    public void whenEnterCorrectCoordinateThenTrue() {
        Board board = new Board();
        IInput input = new ConsoleInput();
        IOut out = new ConsoleOut();
        Validator validator = new Validator(board, out, input);
        assertThat(validator.validateMove("01"), is(true));
    }

    @Test
    public void whenEnterIncorrectCoordinateThenFalse() {
        Board board = new Board();
        IInput input = new ConsoleInput();
        IOut out = new ConsoleOut();
        Validator validator = new Validator(board, out, input);
        assertThat(validator.validateMove("44"), is(false));
    }

    @Test
    public void whenEnterCorrectItemStartGameThenTrue() {
        Board board = new Board();
        IInput input = new ConsoleInput();
        IOut out = new ConsoleOut();
        Validator validator = new Validator(board, out, input);
        assertThat(validator.validateItem("start game"), is(true));
    }

    @Test
    public void whenEnterCorrectItemPlayForOThenTrue() {
        Board board = new Board();
        IInput input = new ConsoleInput();
        IOut out = new ConsoleOut();
        Validator validator = new Validator(board, out, input);
        assertThat(validator.validateItem("play for O"), is(true));
    }

    @Test
    public void whenEnterCorrectItemSizeOptionThenTrue() {
        Board board = new Board();
        IInput input = new ConsoleInput();
        IOut out = new ConsoleOut();
        Validator validator = new Validator(board, out, input);
        assertThat(validator.validateItem("size option"), is(true));
    }

    @Test
    public void whenEnterCorrectItemHelpThenTrue() {
        Board board = new Board();
        IInput input = new ConsoleInput();
        IOut out = new ConsoleOut();
        Validator validator = new Validator(board, out, input);
        assertThat(validator.validateItem("help"), is(true));
    }

    @Test
    public void whenEnterIncorrectItemThenFalse() {
        Board board = new Board();
        IInput input = new ConsoleInput();
        IOut out = new ConsoleOut();
        Validator validator = new Validator(board, out, input);
        assertThat(validator.validateItem("gibberish"), is(false));
    }

    @Test
    public void whenEnterStringWithCoordinatesThenReturnArrayWithTheseCoordinates() {
        Board board = new Board();
        IInput input = new ConsoleInput();
        IOut out = new ConsoleOut();
        Validator validator = new Validator(board, out, input);
        assertThat(validator.parseMove("11"), is(new int[]{1, 1}));
    }

    @Test
    public void whenEnterCorrectBoardSizeThenTrue() {
        Board board = new Board();
        IInput input = new ConsoleInput();
        IOut out = new ConsoleOut();
        Validator validator = new Validator(board, out, input);
        assertThat(validator.validateSize("8"), is(true));
    }
}
