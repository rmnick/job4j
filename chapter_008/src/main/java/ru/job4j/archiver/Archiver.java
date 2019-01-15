package ru.job4j.archiver;

import java.io.File;

public class Archiver {
    public static void main(String[] args) {
        String path = null;
        String[] str = null;
        String name;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    path = args[i + 1];
                    System.out.println(args[i + 1]);
                    break;
                case "-e":
                    str = args[i + 1].split(",");
                    for (String s : str) {
                        System.out.println(s);
                    }
                    break;
                case "-o":
                    name = args[i + 1];
                    System.out.println(args[i + 1]);
                    break;
                    default:
                        break;
            }
        }
        if (path != null && str != null) {
            search(path, str);
        }
    }

    public static void archive(String path, String[] extensions, String name) {

    }

    public static void search(String path, String[] extensions) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File f : files) {
            if (!f.isDirectory()) {
                for (String ext : extensions) {
                    if (f.getName().endsWith("." + ext)) {
                        System.out.println(f.getPath());
                    }
                }
            } else {
                search(f.getPath(), extensions);
            }
        }
    }
}
