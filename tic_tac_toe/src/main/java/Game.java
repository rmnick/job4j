import javafx.scene.transform.Scale;
import model.Board;
import model.Figure;
import model.Logic;
import model.Validator;
import view.ConsolOut;
import view.IOut;

import java.util.Scanner;
import java.util.function.Predicate;

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
        Validator validator = new Validator();
        game.start(validator, consolOut);
    }

    public void showMenu() {

    }

    public void start(Validator validator, IOut consolOut) {
        showMenu();
        Scanner sc = new Scanner(System.in);
        consolOut.printBoard();
        while (!checkWinnerState(consolOut) || !checkDrawState(consolOut)) {
            String mv = sc.nextLine();
            logic.move(new Figure(true), validator.parseMove(mv)[0], validator.parseMove(mv)[1]);
            consolOut.printBoard();
        }
    }

    public boolean checkWinnerState(IOut consolOut) {
        boolean result = false;
        if (!logic.isWinnerX()) {
            consolOut.printAlert("X is winner");
            result = true;
        } else if (!logic.isWinnerO()) {
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
