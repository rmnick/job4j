package ru.job4j.start;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nick Rodionov
 * @since 2018.08.23
 */
public class StartUI {
    /**
     * list constants for menu.
     */
    private Input input;
    private Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void main(String[] args) {
        StartUI start = new StartUI(new ConsolInput(), new Tracker());
        start.init();
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        int key = 0;
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range.add(i);
        }
        while (key != 6) {
            menu.show();
            key = menu.select(input.ask("select:", range));
        }

    }
}
