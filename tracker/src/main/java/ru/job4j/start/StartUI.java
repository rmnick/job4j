package ru.job4j.start;

import ru.job4j.tracker.TrackerSQL;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Nick Rodionov
 * @since 2018.08.23
 */
public class StartUI {
    /**
     * ru.job4j.list constants for menu.
     */
    private final Input input;
    private final ITracker tracker;
    private boolean flag = true;

    public StartUI(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void main(String[] args) {
        try (TrackerSQL tracker = new TrackerSQL()) {
            StartUI start = new StartUI(new ValidateInput(new ConsolInput()), tracker);
            start.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
