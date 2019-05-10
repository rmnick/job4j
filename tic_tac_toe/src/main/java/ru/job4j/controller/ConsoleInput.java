package ru.job4j.controller;

import java.util.Scanner;

public class ConsoleInput implements IInput {
    private Scanner sc;

    public ConsoleInput() {
        sc = new Scanner(System.in);
    }

    @Override
    public String input() {
        return sc.nextLine();
    }
}
