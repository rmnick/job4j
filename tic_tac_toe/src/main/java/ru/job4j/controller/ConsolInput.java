package ru.job4j.controller;

import java.util.Scanner;

public class ConsolInput implements IInput {
    private Scanner sc;

    public ConsolInput() {
        sc = new Scanner(System.in);
    }

    @Override
    public String input() {
        return sc.nextLine();
    }
}
