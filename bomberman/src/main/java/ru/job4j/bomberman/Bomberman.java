package ru.job4j.bomberman;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bomberman implements Runnable {

    private final Board board;

    private Cell position;

    private final List<int[]> directions;


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
        this.position.lock();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (correctStep(direction[0], this.position.getX())
                        && correctStep(direction[1], this.position.getY())) {
                    if (board.move(this.position, destCell(direction))) {
                        this.position = destCell(direction);
                        System.out.println(this.position.getX() + " " + this.position.getY());
                    } else {
                        direction = this.direction();
                        System.out.println("change direction because of block");
                    }
                    Thread.sleep(500);
                } else {
                    direction = this.direction();
                    System.out.println("change direction because of the end of field");
                }
            } catch (InterruptedException e) {
                System.out.println("END");
                Thread.currentThread().interrupt();
            }
        }

    }

    private int[] direction() {
        return directions.get(ThreadLocalRandom.current().nextInt(4));
    }

    private boolean correctStep(int delta, int arg) {
        return (arg + delta < board.size && arg + delta > -1);
    }

    private int step(int arg, int delta) {
        return arg + delta;
    }

    public Cell destCell(int[] direction) {
        return board.getBoard()[step(position.getY(), direction[1])][step(position.getX(), direction[0])];
    }
}
