package ru.job4j.bomberman;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Monster implements Runnable {

    private final Board board;

    private Cell position;

    private final List<int[]> directions;

    private Boolean gameOver;


    public Monster(final Board board, final Cell startPosition, Boolean gameOver) {
        this.board = board;
        this.position = startPosition;
        this.gameOver = gameOver;
        int[] left = {-1, 0};
        int[] right = {1, 0};
        int[] up = {0, -1};
        int[] down = {0, 1};
        directions = Arrays.asList(left, right, up, down);
    }

    public void run() {
        int[] direction = this.direction();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (correctStep(direction[0], this.position.getX())
                        && correctStep(direction[1], this.position.getY())) {
                    if (board.move(this.position, destCell(direction))) {
                        this.position = destCell(direction);
                        System.out.printf("monster %s steps on %d : %d\n", Thread.currentThread().getName(), this.position.getX(), this.position.getY());
                    } else {
                        if (destCell(direction).getNameOwner().equals("Hero")) {
                            System.out.println("Game Over");
                            gameOver = true;
                            Thread.currentThread().interrupt();
                        }
                        direction = this.direction();
                        System.out.printf("monster %s changes direction because of block\n", Thread.currentThread().getName());
                    }
                    Thread.sleep(500);
                } else {
                    direction = this.direction();
                    System.out.printf("monster %s changes direction because of the end of field\n", Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                System.out.printf("monster %s is stop\n", Thread.currentThread().getName());
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
        return board.board[step(position.getY(), direction[1])][step(position.getX(), direction[0])];
    }
}
