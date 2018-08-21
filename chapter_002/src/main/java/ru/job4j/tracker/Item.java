package ru.job4j.tracker;

/**
 * @author Nick Rodionov
 * @since 2018.08.21
 */

public class Item {
    private String name;
    private String description;
    private String id;
    private long date;

    public Item() {

    }

    public Item(String name, String description, long date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return  this.name;
    }

    public String getId() {
        return this.id;
    }

    public long getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }
}
