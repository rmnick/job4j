package ru.job4j.bomberman;

public enum Directions {
    LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1);
    public final int x;
    public final int y;

    Directions(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
