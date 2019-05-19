package ru.job4j;

import ru.job4j.controller.ConsoleInput;
import ru.job4j.controller.IInput;
import ru.job4j.model.*;
import ru.job4j.view.ConsoleOut;
import ru.job4j.view.IOut;


public class Game {
    private final Logic logic;

    public Game(final Logic logic) {
        this.logic = logic;
    }

    public static void main(String[] args) {
        Board board = new Board();
        Logic logic = new Logic(board);
        Game game = new Game(logic);
        IInput consoleInput = new ConsoleInput();
        IOut consoleOut = new ConsoleOut();
        Validator validator = new Validator(board, consoleOut, consoleInput);
        IMenu menu = new MenuConsole(consoleInput, consoleOut, validator, board, logic);
        game.showMenu(menu);
        game.start(consoleOut, ((MenuConsole) menu).getPlayerOne(), ((MenuConsole) menu).getPlayerTwo(), board);
    }

    public void showMenu(IMenu menu) {
        menu.show();
    }

    public void start(IOut consoleOut, IPlayer playerOne, IPlayer playerTwo, Board board) {
        consoleOut.printBoard(board);
        while (!(checkWinnerState(consoleOut) || checkDrawState(consoleOut))) {
            playerOne.move();
            consoleOut.printBoard(board);
            if ((checkWinnerState(consoleOut) || checkDrawState(consoleOut))) {
                break;
            }
            playerTwo.move();
            consoleOut.printBoard(board);
        }
    }

    public boolean checkWinnerState(IOut consolOut) {
        boolean result = false;
        if (logic.isWinnerX()) {
            consolOut.printAlert("X is winner");
            result = true;
        } else if (logic.isWinnerO()) {
            consolOut.printAlert("O is winner");
            result = true;
        }
        return result;
    }

    public boolean checkDrawState(IOut consolOut) {
        boolean result = false;
        if (!logic.hasGap()) {
            consolOut.printAlert("DRAW");
            result = true;
        }
        return result;
    }
}
