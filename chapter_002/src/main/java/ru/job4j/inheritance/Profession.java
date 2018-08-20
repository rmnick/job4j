package ru.job4j.inheritance;

/**
 * @author Nick Rodionov
 * @since 2018.08.20
 */

public class Profession {
    public String name;
    public String profession;

    public Profession() {

    }

    public Profession(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return this.name;
    }

    public String getProfession() {
        return this.profession;
    }
}
