package ru.job4j.unblock;

public class Base {
    private final int id;
    private int version;
    private String name;

    public Base(final int id, final int version, final String name) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return this.name;
    }

    public void incVersion() {
        this.version++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
        this.incVersion();
    }
}
