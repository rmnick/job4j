package ru.job4j.terminal.client;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Validator {
    String[] templates = {"ls", "cd..", "cd", "upload", "download"};

    public boolean validate(String str) {
        boolean result = false;
        String[] arr = str.split(" ");
        if (arr.length <= 2) {
            for (String s : templates) {
                if (arr[0].equals(s)) {
                    result = true;
                    break;
                }
            }
            System.out.println("wrong command name");
        } else {
            System.out.println("you must input command or command with file(directory) name");
        }
        return result;
    }

}
