package ru.job4j;

import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileSearcher extends SimpleFileVisitor<Path> {
    private final PrintWriter pw;
    private final IChecker checker;
    /*
    private final static String keyMask = "-m";
    private final static String keyRegular = "-r";
    private final static String keyName = "-f";
    */


    public FileSearcher(final PrintWriter pw, final IChecker checker) {
        this.pw = pw;
        this.checker = checker;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (checker.check(file)) {
            pw.println(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}
