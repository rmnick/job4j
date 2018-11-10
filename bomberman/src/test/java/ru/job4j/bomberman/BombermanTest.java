package ru.job4j.bomberman;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class BombermanTest {
    @Test
    public void test() throws InterruptedException {
        Board board = new Board(5);
        Cell[][] field = board.getBoard();
        Bomberman bomberman = new Bomberman(board, field[0][0]);
        /*
        for (Cell[] cells : field) {
            for (Cell cell : cells) {
                System.out.printf(" %d, %d \n", cell.getPositionX(), cell.getPositionY());
            }
        }
        */
/*
        while(true) {
            System.out.println(ThreadLocalRandom.current().nextInt(4));
            Thread.currentThread().sleep(500);
        }

        while(true) {
            int[] direction = bomberman.direction();
            System.out.printf("%d, %d \n", direction[0], direction[1]);
            Thread.sleep(500);
        }
        */

    }
}
