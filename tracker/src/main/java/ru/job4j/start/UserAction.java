package ru.job4j.start;
/**
 * @author Nick Rodionov
 * @since 2018.08.26
 */
public interface UserAction {
    /**
     * Method return option's key.
     * @return key
     */
    int key();
    /**
     * Main method.
     * @param input object type of Input
     * @param tracker object type of Tracker
     */
    void execute(Input input, Tracker tracker);
    /**
     * Method return all information about this position.
     * @return menu item.
     */
    String info();
}

