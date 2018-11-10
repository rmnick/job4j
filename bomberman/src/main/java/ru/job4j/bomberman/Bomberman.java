package ru.job4j.bomberman;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bomberman implements Runnable {

    private final Board board;

    private Cell position;

    private List<int[]> directions;


    public Bomberman(final Board board, final Cell startPosition) {
        this.board = board;
        this.position = startPosition;
        int[] left = {-1, 0};
        int[] right = {1, 0};
        int[] up = {0, -1};
        int[] down = {0, 1};
        directions = Arrays.asList(left, right, up, down);
    }

    public void run() {
        int[] direction = this.direction();
        while(true) {
            Cell position = this.position;
            if ((correctStep(position.getPositionX(), direction[0])
                    && correctStep(position.getPositionY(), direction[1]))
                    && board
                    .move(position, destCell(direction))) {
                this.position = destCell(direction);
                System.out.println(this.position.getPositionX() + " " + this.position.getPositionY());
            } else {
                direction = this.direction();
            }
        }

    }

    public int[] direction() {
        return directions.get(ThreadLocalRandom.current().nextInt(4));
    }

    public boolean correctStep(int delta, int arg) {
        return (arg + delta < board.size - 1 && arg + delta > -1);
    }

    public int step(int arg, int delta) {
        return arg + delta;
    }

    public Cell destCell(int [] direction) {
        return board.getBoard()[step(position.getPositionX(), direction[0])][step(position.getPositionY(), direction[1])];
    }
}
