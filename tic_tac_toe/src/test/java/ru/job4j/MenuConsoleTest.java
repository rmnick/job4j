package ru.job4j;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.controller.ConsoleInput;
import ru.job4j.controller.IInput;
import ru.job4j.model.*;
import ru.job4j.test.TestInput;
import ru.job4j.view.ConsoleOut;
import ru.job4j.view.IOut;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuConsoleTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    String menu = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
            .add(Validator.KEY_START)
            .add(Validator.KEY_PLAY_FOR_O)
            .add(Validator.KEY_SIZE)
            .add(Validator.KEY_HELP)
            .add("")
            .add("select item: ").toString();

    @Test
    public void whenEnterCertainSizeAndStartAfterThenBoardSizeHasChanged() {
        Board board = new Board();
        Logic logic = new Logic(board);
        IOut iout = new ConsoleOut();
        List<String> commands = new ArrayList<>();
        commands.add("size option");
        commands.add("6");
        commands.add("start game");
        IInput input = new TestInput(commands);
        Validator validator = new Validator(board, iout, input);
        IMenu menuConsole = new MenuConsole(input, iout, validator, board, logic);
        menuConsole.show();
        assertThat(board.getBoard().length, is(6));
    }

    @Test
    public void whenChooseOAndEnterStartAfterThenUserWillPlayByO() {
        Board board = new Board();
        Logic logic = new Logic(board);
        IOut iout = new ConsoleOut();
        List<String> commands = new ArrayList<>();
        commands.add("play for O");
        commands.add("start game");
        IInput input = new TestInput(commands);
        Validator validator = new Validator(board, iout, input);
        MenuConsole menuConsole = new MenuConsole(input, iout, validator, board, logic);
        menuConsole.show();
        assertThat(menuConsole.getPlayerOne().getClass().getName(), is("ru.job4j.model.SillyRobot"));
    }

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenEnterStartThenShowMenuAndExitFromMenu() {
        Board board = new Board();
        Logic logic = new Logic(board);
        IOut iout = new ConsoleOut();
        IInput input = mock(ConsoleInput.class);
        when(input.input()).thenReturn("start game");
        Validator validator = new Validator(board, iout, input);
        IMenu menuConsole = new MenuConsole(input, iout, validator, board, logic);
        menuConsole.show();
        assertThat(new String(out.toByteArray()), is(menu));
    }

    @Test
    public void whenEnterHelpAndStartAfterThenShowHelpAndExitFromMenu() {
        Board board = new Board();
        Logic logic = new Logic(board);
        IOut iout = new ConsoleOut();
        List<String> commands = new ArrayList<>();
        commands.add("help");
        commands.add("start game");
        IInput input = new TestInput(commands);
        Validator validator = new Validator(board, iout, input);
        IMenu menuConsole = new MenuConsole(input, iout, validator, board, logic);

        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("X starts");
        sb.append(System.lineSeparator());
        sb.append("field coordinates(enter these numbers to put your mark): ");
        sb.append(System.lineSeparator());
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                sb.append(j + "" + i);
                if (j != board.getBoard()[i].length - 1) {
                    sb.append("|");
                }
            }
            sb.append(System.lineSeparator());
            if (i != board.getBoard().length - 1) {
                for (int col = 0; col < board.getBoard().length; col++) {
                    sb.append("-- ");
                }
            }
            sb.append(System.lineSeparator());
        }
        sb.append("player one - X");
        sb.append(System.lineSeparator());
        sb.append("player two - O");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        String help = sb.toString();

        menuConsole.show();
        assertThat(new String(out.toByteArray()), is(menu + help + menu));
    }
}
