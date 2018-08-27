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
        List<Integer> range = new ArrayList<>();
        menu.fillActions(this);
        int key = -1;
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range.add(i);
        }
        do {
            System.out.println(new StringBuilder()
                    .append(System.lineSeparator())
                    .append("Menu: ")
            );
            menu.show();
            key = menu.select(input.ask("select: ", range));
        }
        while (flag);
    }

    public void stop() {
        this.flag = false;
    }
}
