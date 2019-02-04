package ru.job4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;

public class Searcher {
    private final String dir;
    private final String template;
    private final String log;
    private final String keyTemplate;

    public Searcher(final String dir, final String template, final String keyTemplate, final String log) {
        this.dir = dir;
        this.template = template;
        this.log = log;
        this.keyTemplate = keyTemplate;
    }

    public void search() {
        Path path = Paths.get(log + "log.txt");
        try (PrintWriter pw = new PrintWriter(path.toFile())) {
            Files.walkFileTree(Paths.get(dir), new FileSearcher(pw, new CheckerDispatch().init().get(keyTemplate, template)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Validator v = new Validator(args);
        IHelper helper = new ConsoleHelper();
        if (v.validate()) {
            Searcher searcher = new Searcher(v.getDirectory(), v.getTemplate(), v.getKeyTemplate(), v.getDirectoryLog());
            searcher.search();
        } else {
            helper.help();
        }
    }
}
