package ru.job4j.view;

import ru.job4j.model.Board;

public class ConsolOut implements IOut {
    private Board board;

    public ConsolOut(final Board board) {
        this.board = board;
    }
    @Override
    public void printMenu() {

    }

    @Override
    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.board.length; i++) {
            for (int j = 0; j < board.board[i].length; j++) {
                sb.append(board.board[i][j] == null ? " " : board.board[i][j]);
                if (j != board.board[i].length - 1) {
                    sb.append("|");
                }
            }
            sb.append(System.lineSeparator());
            if (i != board.board.length - 1) {
                for (int col = 0; col < board.board.length; col++) {
                    sb.append("— ");
                }
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    @Override
    public void printAlert(final String alert) {
        System.out.println(alert);
    }
}
