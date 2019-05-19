package ru.job4j.model;

import ru.job4j.controller.IInput;
import ru.job4j.view.IOut;

import java.util.HashMap;
import java.util.Map;

public class MenuConsole implements IMenu {
    private boolean start = false;
    private Map<String, IItem> map = new HashMap<>();
    private final IInput input;
    private final IOut out;
    private final Validator validator;
    private final Board board;
    private final Logic logic;
    private IPlayer playerOne;
    private IPlayer playerTwo;


    public MenuConsole(final IInput input, final IOut out, final Validator validator, final Board board, final Logic logic) {
        this.input = input;
        this.out = out;
        this.validator = validator;
        this.board = board;
        this.logic = logic;
        this.playerOne = new User(validator, input, out, logic, true);
        this.playerTwo = new SillyRobot(board, logic, false);
        init();
    }


    /**
     * fill map for dispatching
     */
    public void init() {
        map.put(validator.KEY_START, new Start());
        map.put(validator.KEY_HELP, new Help());
        map.put(validator.KEY_SIZE, new Size());
        map.put(validator.KEY_PLAY_FOR_O, new PlayForO());
    }


    @Override
    public void show() {
        do {
            out.printMenu(this);
            String item = input.input();
            while (!validator.validateItem(item)) {
                out.printAlert("please enter correct menu item: ");
                item = input.input();
            }
            this.map.get(item).run();
        } while (!start);
    }

    private class Start implements IItem {

        @Override
        public void run() {
            start = true;
        }
    }

    /**
     * show help
     */
    private class Help implements IItem {

        @Override
        public void run() {
            out.printHelp(board);
        }
    }

    /**
     * select board size
     */
    private class Size implements IItem {

        @Override
        public void run() {
            out.printAlert("input new size: ");
            String answer = input.input();
            while (!validator.validateSize(answer)) {
                out.printAlert("input correct size: ");
                answer = input.input();
            }
            board.setSize(Integer.valueOf(answer));
        }
    }

    /**
     * choose figure O
     * player O steps second
     */
    private class PlayForO implements IItem {

        @Override
        public void run() {
            playerOne = new SillyRobot(board, logic, true);
            playerTwo = new User(validator, input, out, logic, false);
        }
    }

    public IPlayer getPlayerOne() {
        return this.playerOne;
    }

    public IPlayer getPlayerTwo() {
        return this.playerTwo;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s%s%s%s%sselect item: ",
                Validator.KEY_START,
                System.lineSeparator(),
                Validator.KEY_PLAY_FOR_O,
                System.lineSeparator(),
                Validator.KEY_SIZE,
                System.lineSeparator(),
                Validator.KEY_HELP,
                System.lineSeparator(),
                System.lineSeparator()
        );
    }
}
