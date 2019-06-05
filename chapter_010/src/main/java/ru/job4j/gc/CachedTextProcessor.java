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

public class CachedTextProcessor {
    public static final String PATH = CachedTextProcessor.class.getResource("/textfiles/").getPath();
    public static final String EXIT = "exit";
    private final Map<String, SoftReference<String>> cache = new HashMap<>();

    /**
     * read txt file from resources or from cash
     */
    public String cacheText(String answer) throws IOException {
        String result;
        if (this.cache.get(answer) == null) {
            //read from file, cache string
            StringJoiner sj = new StringJoiner(System.lineSeparator());
            Files.lines(Paths.get(String.format("%s%s.txt", PATH, answer))).forEach(line -> sj.add(line));
            String value = sj.toString();
            //create softReference as value for map and put entry<answer, softReference> to map
            SoftReference<String> sr = new SoftReference<>(value);
            this.cache.put(answer, sr);
            result = value;
        } else {
            //read from cache
            System.out.println("from cache");
            result = this.cache.get(answer).get();
        }
        return result;
    }

    public static void main(String[] args) {
        CachedTextProcessor cp = new CachedTextProcessor();
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
                    result = cp.cacheText(answer);
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
