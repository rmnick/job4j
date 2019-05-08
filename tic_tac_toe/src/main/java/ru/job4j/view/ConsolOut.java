package ru.job4j.view;

import ru.job4j.model.Board;
import ru.job4j.model.IMenu;

public class ConsolOut implements IOut {

    @Override
    public void printMenu(IMenu menu) {
        System.out.println(menu);
    }

    @Override
    public void printBoard(final Board board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                sb.append(board.getBoard()[i][j] == null ? " " : board.getBoard()[i][j]);
                if (j != board.getBoard()[i].length - 1) {
                    sb.append("|");
                }
            }
            sb.append(System.lineSeparator());
            if (i != board.getBoard().length - 1) {
                for (int col = 0; col < board.getBoard().length; col++) {
                    sb.append("— ");
                }
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    public void printHelp(final Board board) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("X starts");
        sb.append(System.lineSeparator());
        sb.append("field coordinates(enter these numbers to put your mark): ");
        sb.append(System.lineSeparator());
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                sb.append(j + "" + i);
                if (j != board.getBoard()[i].length - 1) {
                    sb.append("|");
                }
            }
            sb.append(System.lineSeparator());
            if (i != board.getBoard().length - 1) {
                for (int col = 0; col < board.getBoard().length; col++) {
                    sb.append("—— ");
                }
            }
            sb.append(System.lineSeparator());
        }
        sb.append("player one - X");
        sb.append(System.lineSeparator());
        sb.append("player two - O");
        sb.append(System.lineSeparator());
        System.out.println(sb.toString());
    }


    @Override
    public void printAlert(final String alert) {
        System.out.println(alert);
    }
}
