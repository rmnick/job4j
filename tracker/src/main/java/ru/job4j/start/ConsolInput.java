package ru.job4j.start;

import java.util.ArrayList;
import java.util.List;
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

    public int ask(String question, List<Integer> list) {
        int key = Integer.valueOf(this.ask(question));
        boolean flag = false;
        for (Integer item : list) {
            if (item == key) {
                flag = true;
                break;
            }
            //else {
              //  key = -1;
            //}
        }
        return key;
    }

}
