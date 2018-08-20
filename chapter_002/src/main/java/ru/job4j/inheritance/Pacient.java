package ru.job4j.inheritance;

/**
 * @author Nick Rodionov
 * @since 2018.08.20
 */

public class Pacient {
    private String name;

    public Pacient() {

    }

    public Pacient(String name) {
        this.name = name;
    }

    public String getName() {
        return  this.name;
    }
}
