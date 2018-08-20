package ru.job4j.inheritance;

/**
 * @author Nick Rodionov
 * @since 2018.08.20
 */

public class Student {
    private String name;

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return  this.name;
    }
}
