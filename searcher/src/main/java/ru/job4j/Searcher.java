package ru.job4j;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Searcher {
    private final String dir;
    private final String name;
    private final String log;

    public Searcher(final String dir, final String name, final String log) {
        this.dir = dir;
        this.name = name;
        this.log = log;
    }

    /**
     * main method use the walkFileTree
     * @param var
     */
    public void search(String var) {
        Path path = Paths.get(log + "log.txt");
        try (PrintWriter pw = new PrintWriter(path.toFile())) {
            Files.walkFileTree(Paths.get(dir), new MyVisitor(name, pw, var));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyVisitor extends SimpleFileVisitor<Path> {
        private final String name;
        private final PrintWriter pw;
        private final String var;

        public MyVisitor(final String name, final PrintWriter pw, final String var) {
            this.name = name;
            this.pw = pw;
            this.var = var;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (var.equals("m") ? maskMatches(file)
                    : (var.equals("r") ? regularMatches(file) : fullMatches(file))) {
                    pw.println(file.toAbsolutePath());
            }
            return FileVisitResult.CONTINUE;
        }

        private boolean regularMatches(Path file) {
            boolean result = false;
            if (file.getFileName().toString().matches(name)) {
                result = true;
            }
            return result;
        }

        private boolean fullMatches(Path file) {
            boolean result = false;
            if (file.getFileName().toString().equals(name)) {
                result = true;
            }
            return result;
        }

        private boolean maskMatches(Path file) {
            boolean result = false;
            if (file.getFileName().toString().endsWith(name)) {
                result = true;
            }
            return result;
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
            searcher.search(var);
        } else {
            help();
        }
    }
}
