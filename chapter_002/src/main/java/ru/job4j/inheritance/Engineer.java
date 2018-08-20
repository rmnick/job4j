package ru.job4j.inheritance;

/**
 * @author Nick Rodionov
 * @since 2018.08.20
 */

public class Engineer extends Profession {

    public Engineer(String name) {
        this.name = name;
    }

    public void build(House house) {
    }
}
