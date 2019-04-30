package ru.job4j;

import ru.job4j.model.*;
import ru.job4j.view.ConsolOut;
import ru.job4j.view.IOut;

import java.util.Scanner;

public class Game {
    private final Logic logic;

    public Game(final Logic logic) {
        this.logic = logic;
    }

    public static void main(String[] args) {
        Board board = new Board();
        Logic logic = new Logic(board);
        Game game = new Game(logic);
        IOut consolOut = new ConsolOut(board);
        Validator validator = new Validator(board, consolOut);
        IRobot robot = new SillyRobot(board);
        game.start(validator, consolOut, robot);
    }

    public void showMenu() {

    }

    public void start(Validator validator, IOut consolOut, IRobot robot) {
        showMenu();
        Scanner sc = new Scanner(System.in);
        consolOut.printBoard();
        while (!(checkWinnerState(consolOut) || checkDrawState(consolOut))) {
            String mv = sc.nextLine();
            while (!logic.move(new Figure(true), validator.parseMove(mv)[0], validator.parseMove(mv)[1])) {
                consolOut.printAlert("the cell is busy");
                mv = sc.nextLine();
            }
            consolOut.printBoard();
            if ((checkWinnerState(consolOut) || checkDrawState(consolOut))) {
                break;
            }
            int[] robotMoves = robot.move();
            logic.move(new Figure(false), robotMoves[0], robotMoves[1]);
            consolOut.printBoard();
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
