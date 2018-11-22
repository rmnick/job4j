package ru.job4j.bomberman;

public class Game {

    public static void main(String[] args) {
        Boolean flag = new Boolean(false);
        Board board = new Board(10);
        Bomberman bomberman = new Bomberman(board, board.board[0][0]);
        Thread hero = new Thread(bomberman, "Hero");
        board.board[0][5].lock();
        board.board[5][0].lock();
        board.board[4][4].lock();
        Thread monster1 = new Thread(new Monster(board, new Cell(9, 9), flag), "Monster1");
        Thread monster2 = new Thread(new Monster(board, new Cell(9, 0), flag), "Monster2");
        Thread monster3 = new Thread(new Monster(board, new Cell(0, 9), flag), "Monster3");

        hero.start();
        monster1.start();
        monster2.start();
        monster3.start();
    }
}
