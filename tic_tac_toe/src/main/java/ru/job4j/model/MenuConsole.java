package ru.job4j.model;

import ru.job4j.controller.IInput;
import ru.job4j.view.IOut;

import java.util.HashMap;
import java.util.Map;

public class MenuConsole implements IMenu {
    private boolean exit = false;
    private Map<String, IItem> map = new HashMap<>();
    private final IInput input;
    private final IOut out;
    private final Validator validator;
    private final Board board;


    public MenuConsole(final IInput input, final IOut out, final Validator validator, final Board board) {
        this.input = input;
        this.out = out;
        this.validator = validator;
        this.board = board;
        init();
    }



    public void init() {
        map.put(validator.KEY_START, new Start());
        map.put(validator.KEY_HELP, new Help());
        map.put(validator.KEY_SIZE, new Size());
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
        } while (!exit);

    }

    private class Start implements IItem {

        @Override
        public void run() {
            exit = true;
        }
    }

    private class Help implements IItem {

        @Override
        public void run() {
            out.printHelp(board);
        }
    }

    private class Size implements IItem {

        @Override
        public void run() {
            out.printAlert("input new size: ");
            String answer = input.input();
            while (!validator.validateSize(answer)) {
                out.printAlert("input coorect size: ");
                answer = input.input();
            }
            board.setSize(Integer.valueOf(answer));
        }
    }

    @Override
    public String toString() {
        return String.format("start game%splayer one%splayer two%ssize option%shelp%s%sselect item: ",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator()
        );
    }
}
