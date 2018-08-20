package ru.job4j.inheritance;

/**
 * @author Nick Rodionov
 * @since 2018.08.20
 */

public class Doctor extends Profession {

    Doctor(String name) {
        this.name = name;
    }

    public Diagnose heal(Pacient pacient) {
        return new Diagnose();
    }
}
