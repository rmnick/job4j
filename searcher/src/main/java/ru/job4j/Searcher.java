package ru.job4j;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class Searcher {
    private final String dir;
    private final String template;
    private final String log;
    private final String keyTemplate;
    private final static String KEY_MASK = "-m";
    private final static String KEY_FULL_NAME = "-f";
    //private final static String KEY_REGULAR = "-r";


    public Searcher(final String dir, final String template, final String keyTemplate, final String log) {
        this.dir = dir;
        this.template = template;
        this.log = log;
        this.keyTemplate = keyTemplate;
    }

    public IChecker getChecker() {
        IChecker checker;
        if (keyTemplate.equals(KEY_MASK)) {
            checker = new MaskCheckerMaker().makeChecker(template);
        } else if (keyTemplate.equals(KEY_FULL_NAME)) {
            checker = new NameCheckerMaker().makeChecker(template);
        } else {
            checker = new RegularCheckerMaker().makeChecker(template);
        }
        return checker;
    }

    public void search() {
        Path path = Paths.get(log + "log.txt");
        try (PrintWriter pw = new PrintWriter(path.toFile())) {
            Files.walkFileTree(Paths.get(dir), new FileSearcher(pw, this.getChecker()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        //String dir = null;
        //String template = null;
       // String keyTemplate = null;
       // String log = null;
        Map<String, String> keys = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                  //  dir = args[i + 1];
                    keys.putIfAbsent("-d", args[i + 1]);
                    break;
                case "-n":
                   // template = args[i + 1];
                    keys.putIfAbsent("-n", args[i + 1]);
                    break;
                case "-m":
                   // keyTemplate = "-m";
                    keys.putIfAbsent("keyTemplate", "-m");
                    break;
                case "-f":
                    //keyTemplate = "-f";
                    keys.putIfAbsent("keyTemplate", "-f");
                    break;
                case "-r":
                   // keyTemplate = "-r";
                    keys.putIfAbsent("keyTemplate", "-r");
                    break;
                case "-o":
                    //log = args[i + 1];
                    keys.putIfAbsent("-o", args[i + 1]);
                    break;
                    default:
                        break;
            }

        }
        if (!valid(args)) {
            Searcher searcher = new Searcher(keys.get("-d"), keys.get("-n"), keys.get("keyTemplate"), keys.get("-o"));
            searcher.search();
        } else {
            help();
        }
    }
}
