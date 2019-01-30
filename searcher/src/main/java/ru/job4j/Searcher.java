package ru.job4j;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class Searcher {
    private final String dir;
    private final String template;
    private final String log;

    public Searcher(final String dir, final String template, final String log) {
        this.dir = dir;
        this.template = template;
        this.log = log;
    }


    public void search() {
        Path path = Paths.get(log + "log.txt");
        IChecker checker = new MaskCheckerMaker(template).makeChecker();
        try (PrintWriter pw = new PrintWriter(path.toFile())) {
            Files.walkFileTree(Paths.get(dir), new FileSearcher(pw, checker));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * validate args and print hint
     * @param args
     * @return
     */
    public static boolean valid(String[] args) {
        boolean flag = false;
        if (args.length == 7) {
            for (int i = 0; i < args.length; i++) {
                if (flag) {
                    break;
                }
                switch (i) {
                    case 0:
                        if (!args[i].equals("-d")) {
                            System.out.println("write correct key \"-d\"");
                            flag = true;
                        } else {
                            File dir = new File(args[i + 1]);
                            if (!dir.exists() && !dir.isDirectory()) {
                                System.out.println("wrong directory name");
                                flag = true;
                            }
                        }
                        break;
                    case 2:
                        if (!args[i].equals("-n")) {
                            System.out.println("write correct key \"-n\"");
                            flag = true;
                        }
                        break;
                    case 4:
                        if (!(args[i].equals("-m") || args[i].equals("-f") || args[i].equals("-r"))) {
                            System.out.println("write correct key \"-m\" or \"-f\" or \"-r\"");
                            flag = true;
                        }
                        break;
                    case 5:
                        if (!args[i].equals("-o")) {
                            System.out.println("write correct key \"-o\"");
                            flag = true;
                        } else {
                            File dirLog = new File(args[i + 1]);
                            if (!dirLog.exists() && !dirLog.isDirectory()) {
                                System.out.println("wrong directory log file name");
                                flag = true;
                            }
                            break;
                        }
                    default:
                        break;
                }
            }
        } else {
            System.out.println("please write the keys correct");
            flag = true;
        }
        return flag;
    }

    /**
     * print help in console
     */
    public static void help() {
        System.out.println(new StringBuilder()
                .append("help: ")
                .append(System.lineSeparator())
                .append("-d \"absolute path\" -n \"filename(regular expression or mask)\" "
                        + "-m,r,f(mask, regular expression, full name) -o \"log file absolute path\"")
                .append(System.lineSeparator())
        );
    }


    public static void main(String[] args) {
        String dir = null;
        String name = null;
        String var = null;
        String log = null;
        Map<String, String> keys = new HashMap<>();
        String[] k = {"-d", "-n", "-m", "-r", "-f", "-o"};
        for (String s : k) {
            keys.put(s, null);
        }
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    dir = args[i + 1];
                    break;
                case "-n":
                    name = args[i + 1];
                    break;
                case "-m":
                    var = "m";
                    break;
                case "-f":
                    var = "f";
                    break;
                case "-r":
                    var = "r";
                    break;
                case "-o":
                    log = args[i + 1];
                    break;
                    default:
                        break;
            }

        }
        if (!valid(args)) {
            Searcher searcher = new Searcher(dir, name, log);
            searcher.search();
        } else {
            help();
        }
    }
}
