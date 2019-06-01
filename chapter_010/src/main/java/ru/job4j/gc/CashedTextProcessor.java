package ru.job4j.gc;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class CashedTextProcessor {
    public static final String PATH = CashedTextProcessor.class.getResource("/textfiles/").getPath();
    public static final String EXIT = "exit";
    public final Map<String, String> cash = new HashMap<>();

    public static void main(String[] args) {
        CashedTextProcessor pr = new CashedTextProcessor();
        Scanner sc = new Scanner(System.in);
        String answer;
        System.out.println("input file name");
        String result;
        do {
            //validated answer is a key for map
            answer = sc.nextLine().toLowerCase().trim();
            if (answer.toLowerCase().trim().equals(EXIT)) {
                break;
            }
            try {
                File file = new File(String.format("%s%s.txt", PATH, answer));
                if (file.exists() && !file.isDirectory()) {
                    //check reference existing
                    result = pr.cash.get(answer);
                    if (result == null) {
                        System.out.println("from file: ");
                        StringJoiner sj = new StringJoiner(System.lineSeparator());
                        Files.lines(Paths.get(String.format("%s%s.txt", PATH, answer))).forEach(line -> sj.add(line));
                        String value = sj.toString();
                        //create softReference as value for map
                        SoftReference<String> sr = new SoftReference<>(value);
                        pr.cash.put(answer, sr.get());
                        result = value;
                    } else {
                        System.out.println("from cash: ");
                        //get strong reference from soft
                        result = pr.cash.get(answer);
                    }
                    System.out.println(result);
                } else {
                    System.out.println("input correct name");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%scontinue", System.lineSeparator()));
        } while (true);
    }
}
