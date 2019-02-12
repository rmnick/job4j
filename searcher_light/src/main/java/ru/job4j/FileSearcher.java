package ru.job4j;

import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

public class FileSearcher extends SimpleFileVisitor<Path> {
    private final PrintWriter pw;
    private final Predicate<Path> predicate;

    public FileSearcher(final PrintWriter pw, final Predicate<Path> predicate) {
        this.pw = pw;
        this.predicate = predicate;

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (predicate.test(file)) {
            System.out.println(file.getFileName());
            pw.println(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}
