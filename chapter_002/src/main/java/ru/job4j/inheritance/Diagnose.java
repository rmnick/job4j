package ru.job4j.inheritance;

/**
 * @author Nick Rodionov
 * @since 2018.08.20
 */

public class Diagnose {
    private String name;

    public Diagnose() {

    }

    public Diagnose(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
