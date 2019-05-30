package ru.job4j.view;

import ru.job4j.model.Board;
import ru.job4j.model.IMenu;

public interface IOut {
    void printMenu(IMenu menu);
    void printBoard(Board board);
    void printAlert(String str);
    void printHelp(Board board);
}
