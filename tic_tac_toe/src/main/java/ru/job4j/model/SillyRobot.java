package ru.job4j.model;

public class SillyRobot implements IRobot {
    private Board board;
    private Logic logic;

    public SillyRobot(final Board board, final Logic logic) {
        this.board = board;
        this.logic = logic;
    }

    @Override
    public int[] findWay() {
        int [] result = {0, 0};
        do {
            result[0] = gen();
            result[1] = gen();
            System.out.println("robot move" + " " + result[0] + " " + result[1]);
        } while (board.board[result[1]][result[0]] != null);
        return result;
    }

    @Override
    public void move() {
        int[] way = this.findWay();
        logic.move(new Figure(false), way[0], way[1]);
    }

    public int gen() {
        return (int) (Math.random() * 3);
    }
}
