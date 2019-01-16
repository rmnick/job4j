package ru.job4j.chat;

import java.io.*;
import java.util.Scanner;

public class Bot {
    public static void main(String[] args) {
        String answer = null;
        Scanner sc = new Scanner(System.in);
        File file = new File("chapter_008/src/main/resources/file.txt");
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            answer = sc.nextLine();
            while (!answer.equals("exit")) {
                if (sc.hasNextLine()) {
                    if (answer.equals("stop")) {
                        System.out.println("!");
                    } else {
                       // answer = in.readLine();
                        System.out.println("fuck");
                    }
                    answer = sc.nextLine();
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
