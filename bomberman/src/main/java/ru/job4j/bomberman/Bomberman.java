package ru.job4j.bomberman;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Bomberman {

    private final Board board;

    private Cell position;


    public Bomberman(final Board board, final Cell startPosition) {
        this.board = board;
        this.position = startPosition;
    }

    //input control command
    public void command(Directions dir) throws InterruptedException {
        switch (dir) {
            case UP:
                move(Directions.UP);
                break;
            case DOWN:
                move(Directions.UP);
                break;
            case LEFT:
                move(Directions.LEFT);
                break;
            case RIGHT:
                move(Directions.RIGHT);
                break;
                default:
                    break;
        }

    }

    private void move(Directions dir) throws InterruptedException {
        //lock start position
        if (!this.position.isHeldByCurrentThread()) {
            this.position.lock();
        }
        //movement logic
        if (correctStep(dir.x, this.position.getX())
                && correctStep(dir.y, this.position.getY())) {
            if (this.destCell(dir).tryLock(500, TimeUnit.MILLISECONDS)) {
                this.position.unlock();
                this.position = this.destCell(dir);
                System.out.printf("Bomberman steps on %d : %d \n", this.position.getX(), this.position.getY());
            }
        }
    }

    private boolean correctStep(int delta, int arg) {
        return (arg + delta < board.size && arg + delta > -1);
    }

    private int step(int arg, int delta) {
        return arg + delta;
    }

    public Cell destCell(Directions dir) {
        return board.board[step(position.getY(), dir.y)][step(position.getX(), dir.x)];
    }
}

