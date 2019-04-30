package ru.job4j.model;

public class SillyRobot implements IRobot {
    private Board board;

    public SillyRobot(final Board board) {
        this.board = board;
    }

    @Override
    public int[] move() {
        int [] result = {0, 0};
        do {
            result[0] = gen();
            result[1] = gen();
            System.out.println("robot move" + " " + result[0] + " " + result[1]);
        } while (board.board[result[1]][result[0]] != null);
        return result;
    }

    public int gen() {
        return (int) (Math.random() * 3);
    }
}
