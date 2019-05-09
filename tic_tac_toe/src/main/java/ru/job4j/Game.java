package ru.job4j;

import ru.job4j.controller.ConsolInput;
import ru.job4j.controller.IInput;
import ru.job4j.model.*;
import ru.job4j.view.ConsolOut;
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
        IInput consolInput = new ConsolInput();
        IOut consolOut = new ConsolOut();
        Validator validator = new Validator(board, consolOut, consolInput);
        IMenu menu = new MenuConsole(consolInput, consolOut, validator, board, logic);
        game.showMenu(menu);
        game.start(consolOut, ((MenuConsole) menu).getPlayerOne(), ((MenuConsole) menu).getPlayerTwo(), board);
    }

    public void showMenu(IMenu menu) {
        menu.show();
    }

    public void start(IOut consolOut, IPlayer playerOne, IPlayer playerTwo, Board board) {
        consolOut.printBoard(board);
        while (!(checkWinnerState(consolOut) || checkDrawState(consolOut))) {
            playerOne.move();
            consolOut.printBoard(board);
            if ((checkWinnerState(consolOut) || checkDrawState(consolOut))) {
                break;
            }
            playerTwo.move();
            consolOut.printBoard(board);
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
