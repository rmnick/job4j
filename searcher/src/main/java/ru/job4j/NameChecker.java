package ru.job4j;

import java.nio.file.Path;

public class NameChecker implements IChecker {

    private final String template;

    public NameChecker(final String template) {
        this.template = template;
    }

    @Override
    public boolean check(Path file) {
        boolean result = false;
        if (file.getFileName().toString().equals(template)) {
            result = true;
        }
        return result;
    }
}
