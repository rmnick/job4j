package ru.job4j.search;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.Files.walkFileTree;

public class Searcher {
    String dir;
    String name;

    public Searcher(String dir, String name) {
        this.dir = dir;
        this.name = name;
    }

    public void search() throws IOException {
        Files.walkFileTree(Paths.get(dir), new MyVisitor(name));
    }

    private class MyVisitor extends SimpleFileVisitor<Path> {
        private final String name;
       // private final Path

        public MyVisitor(String name) {
            this.name = name;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.getFileName().toString().equals(name)) {
                System.out.println(file.toAbsolutePath());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
        Searcher srch = new Searcher("C:\\projects\\job4j\\chapter_008", "StringSorter.java");
        srch.search();
    }
}
