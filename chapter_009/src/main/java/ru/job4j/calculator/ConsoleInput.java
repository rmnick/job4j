package ru.job4j.calculator;

import java.util.Scanner;

public class ConsoleInput implements IInput{
    private Scanner sc = new Scanner(System.in);
    @Override
    public String ask() {
        System.out.println("enter something");
        String answer = sc.nextLine();
        return answer;
    }
}
