package ru.job4j.bomberman;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private Difficulty difficulty;
    private Board board;
    private Bomberman hero;
    private LinkedList<Thread> monsters = new LinkedList<>();
    private Blocks blocks;

    public Game(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.initialize(difficulty);
        hero = new Bomberman(board, board.board[0][0]);
    }

    private void initialize(Difficulty difficulty) {
        board = new Board(difficulty.sizeBoard);
        this.monsters = this.createMonsters(difficulty);
        blocks = new Blocks(createBlocks(difficulty));
    }

    private LinkedList<Thread> createMonsters(Difficulty difficulty) {
        LinkedList<Thread> monsters = new LinkedList<>();
        for (int i = 0; i < difficulty.numberOfMonsters; i++) {
            if (i == 0) {
                monsters.add(new Thread(new Monster(board, new Cell(difficulty.sizeBoard - 1, difficulty.sizeBoard - 1), difficulty.speed), String.format("Monster %d", i)));
            }
            if (i == 1) {
                monsters.add(new Thread(new Monster(board, new Cell(difficulty.sizeBoard - 1, 0), difficulty.speed), String.format("Monster %d", i)));
            }
            if (i == 2) {
                monsters.add(new Thread(new Monster(board, new Cell(0, difficulty.sizeBoard - 1), difficulty.speed), String.format("Monster %d", i)));
            }
        }
        return monsters;
    }

    private Cell[] createBlocks(Difficulty difficulty) {
        Cell[] cells = new Cell[difficulty.numberOfBlocks];
        for (int i = 0; i < difficulty.numberOfBlocks; i++) {
            int[] coordinates = generateCoordinates(difficulty);
            System.out.printf("cell with coordinate %d : %d has a block \n", coordinates[1], coordinates[0]);
            cells[i] = board.board[coordinates[0]][coordinates[1]];
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return cells;
    }

// check occupied spots and return validate coordinates for blocks
    private int[] generateCoordinates(Difficulty difficulty) {
        int[] coordinates = new int[2];
        coordinates[0] = ThreadLocalRandom.current().nextInt(difficulty.sizeBoard);
        coordinates[1] = ThreadLocalRandom.current().nextInt(difficulty.sizeBoard);
        return (!(Arrays.equals(coordinates, new int[]{difficulty.sizeBoard - 1, 0})
                || Arrays.equals(coordinates, new int[]{difficulty.sizeBoard - 1, difficulty.sizeBoard - 1})
                || Arrays.equals(coordinates, new int[]{0, difficulty.sizeBoard - 1})
                || Arrays.equals(coordinates, new int[]{0, 0}))) ? coordinates : generateCoordinates(difficulty);
    }

    public void start() throws InterruptedException {
        System.out.println("START GAME");
        Thread block = new Thread(blocks, "Blocks");
        block.setDaemon(true);
        block.start();
        Thread.sleep(2000);
        for (Thread thread : monsters) {
            thread.setDaemon(true);
            thread.start();
        }
        new Thread(hero, "BOMBERMAN").start();
    }

    public static void main(String[] args) {
        //you can use for levels of difficulty (EASY, MEDIUM, HARD, GOD_MODE)
       Game game = new Game(Difficulty.HARD);
       try {
           game.start();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }
}
