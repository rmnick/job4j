package ru.job4j;

import org.junit.Test;
import ru.job4j.controller.ConsoleInput;
import ru.job4j.controller.IInput;
import ru.job4j.model.*;
import ru.job4j.view.ConsoleOut;
import ru.job4j.view.IOut;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {
    @Test
    public void whenCorrectMoveThenAddFigureOnBoard() {
        Board board = new Board();
        Logic logic = new Logic(board);
        IOut out = new ConsoleOut();
        IInput input = mock(ConsoleInput.class);
        when(input.input()).thenReturn("11");
        Validator validator = new Validator(board, out, input);
        boolean mark = true;
        IPlayer user = new User(validator, input, out, logic, mark);
        user.move();
        assertThat(board.getBoard()[1][1].getMark(), is(true));
    }
}
