package ru.job4j.bomberman;

public enum Difficulty {
    EASY(10, 1, 3, 1000), MEDIUM(15, 2, 5, 500), HARD(20, 3, 7, 200), GOD_MODE(5, 3, 1, 10);

    public int sizeBoard;
    public int numberOfMonsters;
    public int numberOfBlocks;
    public int speed;

    Difficulty(int size, int number, int blocks, int speed) {
        this.sizeBoard = size;
        this.numberOfMonsters = number;
        this.numberOfBlocks = blocks;
        this.speed = speed;
    }
}
