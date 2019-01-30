package ru.job4j;

import java.nio.file.Path;

public class NameChecker implements IChecker {

    private final Path file;
    private final String template;

    public NameChecker(final Path file, final String template) {
        this.file = file;
        this.template = template;
    }

    @Override
    public boolean check() {
        boolean result = false;
        if (file.getFileName().toString().equals(template)) {
            result = true;
        }
        return result;
    }
}
