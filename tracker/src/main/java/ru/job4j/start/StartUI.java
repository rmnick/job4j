package ru.job4j.start;

import java.util.List;

/**
 * @author Nick Rodionov
 * @since 2018.08.23
 */
public class StartUI {
    /**
     * list constants for menu.
     */
    private final Input input;
    private final Tracker tracker;
    private boolean flag = true;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void main(String[] args) {
        StartUI start = new StartUI(new ValidateInput(new ConsolInput()), new Tracker());
        start.init();
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        menu.fillKeys();
        List<Integer> range = menu.getKeys();
        do {
            menu.show(System.out::println);
            menu.select(input.ask("select: ", range));
        }
        while (flag);
    }

    public void stop() {
        this.flag = false;
    }
}
