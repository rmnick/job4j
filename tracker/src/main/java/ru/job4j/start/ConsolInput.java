package ru.job4j.start;

import java.util.Scanner;

/**
 * @author Nick Rodionov
 * @since 2018.08.23
 */
public class ConsolInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }

}
