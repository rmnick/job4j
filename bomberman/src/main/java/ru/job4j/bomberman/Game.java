package ru.job4j.bomberman;

import java.util.LinkedList;

public class Game {

    private Difficulty difficulty;
    private Board board;
    private Bomberman hero;
    private LinkedList<Thread> monsters = new LinkedList<>();
    private Blocks blocks;

    public Game(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    {
        switch (this.difficulty) {
            case EASY:
                board = new Board(Difficulty.EASY.sizeBoard);
                this.monsters = this.createMonsters(Difficulty.EASY);
                break;
            case MEDIUM:
                board = new Board(15);
                break;
            case HARD:
                board = new Board(20);
                break;
            case GOD_MODE:
                board = new Board(5);
                break;
            default:
                break;
        }
    }

    public LinkedList<Thread> createMonsters(Difficulty difficulty) {
        LinkedList<Thread> monsters = new LinkedList<>();
        for (int i = 0; i < difficulty.numberOfMonsters; i++) {
            monsters.add(new Thread(new Monster(board, new Cell(difficulty.sizeBoard - 1, difficulty.sizeBoard - 1)), String.format("Monster %d", i)));
        }
        return monsters;
    }

    public void start(Board board, Blocks blocks, LinkedList<Monster> monsters, Bomberman hero) {

    }

    public static void main(String[] args) {
        Board board = new Board(10);
        Bomberman bomberman = new Bomberman(board, board.board[0][0]);
        Thread monster1 = new Thread(new Monster(board, new Cell(9, 9)), "Monster1");
        Thread monster2 = new Thread(new Monster(board, new Cell(9, 0)), "Monster2");
        Thread monster3 = new Thread(new Monster(board, new Cell(0, 9)), "Monster3");
        monster1.start();
        monster2.start();
        monster3.start();
        Thread blocks = new Thread(new Blocks(new Cell[]{board.board[9][8], board.board[8][9], board.board[7][2]}));
        blocks.start();
        while (!board.gameOver) {
            try {
                bomberman.command(Directions.UP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        monster1.interrupt();
        monster2.interrupt();
        monster3.interrupt();
        blocks.interrupt();

    }
}
