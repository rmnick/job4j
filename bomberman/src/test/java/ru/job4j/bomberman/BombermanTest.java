package ru.job4j.bomberman;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class BombermanTest {
    @Test
    public void test() throws InterruptedException {
        Board board = new Board(10);
        Cell[][] field = board.getBoard();
        Bomberman bomberman = new Bomberman(board, field[0][0]);
        Thread thread = new Thread(bomberman);
        //make some blocks
        field[0][5].lock();
        field[5][0].lock();
        field[4][4].lock();
        //start bomberman
        thread.start();
        //life time of our hero
        Thread.sleep(30000);
        //stop game
        thread.interrupt();
    }
}
