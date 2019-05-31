package ru.job4j.gc;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class CashedTextProcessor {
    public static final URL PATH = CashedTextProcessor.class.getResource("/textfiles/");
    public static final String EXIT = "exit";
    public final Map<String, String> cash = new HashMap<>();

    public static void main(String[] args) {
        CashedTextProcessor pr = new CashedTextProcessor();
        Scanner sc = new Scanner(System.in);
        String answer;
        System.out.println("input file name");
        String result;
        do {
            answer = sc.nextLine().toLowerCase().trim();
            if (answer.toLowerCase().trim().equals(EXIT)) {
                break;
            }
            try {
//                System.out.println(Paths.get(String.format("%s%s.txt", PATH, answer)));
                File file = new File(String.format("%s%s.txt", PATH.getPath(), answer));
                if (file.exists() && !file.isDirectory()) {
                    result = pr.cash.get(answer);
                    if (result == null) {
                        System.out.println("from file: ");
                        StringJoiner sj = new StringJoiner(System.lineSeparator());
                        Files.lines(Paths.get(String.format("%s%s.txt", PATH.getPath(), answer)), StandardCharsets.UTF_8).forEach(line -> sj.add(line));
                        String value = sj.toString();
                        SoftReference<String> sr = new SoftReference<>(value);
                        pr.cash.put(answer, sr.get());
                        result = value;
                    } else {
                        System.out.println("from cash: ");
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
