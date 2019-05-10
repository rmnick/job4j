package ru.job4j.model;

/**
 * simple bot with random moving behavior
 */
public class SillyRobot implements IRobot {
    private Board board;
    private Logic logic;
    private boolean mark;

    public SillyRobot(final Board board, final Logic logic, final boolean mark) {
        this.board = board;
        this.logic = logic;
        this.mark = mark;
    }

    /**
     * search empty cell
     * @return int[]
     */
    @Override
    public int[] findWay() {
        int[] result = {0, 0};
        do {
            result[0] = gen();
            result[1] = gen();
        } while (board.getBoard()[result[1]][result[0]] != null);
        return result;
    }

    @Override
    public void move() {
        int[] way = this.findWay();
        logic.move(new Figure(this.mark), way[0], way[1]);
    }

    public int gen() {
        return (int) (Math.random() * 3);
    }
}
