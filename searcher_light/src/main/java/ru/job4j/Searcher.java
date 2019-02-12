package ru.job4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class Searcher {
    private final String dir;
    private final String template;
    private final String log;
    private final String keyTemplate;
    public final static String FULL_NAME = "-f";
    public final static String REGULAR = "-r";
    public final static String MASK = "-m";

    public Searcher(final String dir, final String template, final String keyTemplate, final String log) {
        this.dir = dir;
        this.template = template;
        this.log = log;
        this.keyTemplate = keyTemplate;
    }

    public void search() {
        Path path = Paths.get(log + "log.txt");
        try (PrintWriter pw = new PrintWriter(path.toFile())) {
            Files.walkFileTree(Paths.get(dir), new FileSearcher(pw, match()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * choose method for comparison files in searching according with keys(-r, -f, -m)
     * -r : regular expression
     * -f : full name of file
     * -m : some mask
     * @return
     */
    private Predicate<Path> match() {
        Predicate<Path> result;
        if (keyTemplate.equals(FULL_NAME)) {
            result =  file -> {
              return file.getFileName().toString().equals(template);
            };
        } else if (keyTemplate.equals(MASK)) {
            result = file -> {
              return file.getFileName().toString().endsWith(template);
            };
        } else {
            result = file -> {
               return file.getFileName().toString().matches(template);
            };
        }
        return result;
    }

    public static void main(String[] args) {
        Validator v = new Validator(args);
        Helper helper = new Helper();
        if (v.validate()) {
            Searcher searcher = new Searcher(v.getDirectory(), v.getTemplate(), v.getKeyTemplate(), v.getDirectoryLog());
            searcher.search();
        } else {
            helper.help();
        }
    }
}
