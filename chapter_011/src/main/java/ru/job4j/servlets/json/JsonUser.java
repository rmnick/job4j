package ru.job4j.servlets.json;

import java.util.StringJoiner;

public class JsonUser {
    private String id;
    private String name;
    private String lastName;

    public JsonUser() {

    }

    public JsonUser(final String id, final String name, final String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        sj.add(this.getId());
        sj.add(this.getName());
        sj.add(this.lastName);
        return sj.toString();
    }
}
