package ru.job4j;

import java.nio.file.Path;

public class RegularChecker implements IChecker {

    private final String template;

    public RegularChecker(final String template) {
        this.template = template;
    }

    @Override
    public boolean check(Path file) {
        boolean result = false;
        if (file.getFileName().toString().matches(template)) {
            result = true;
        }
        return result;
    }
}
