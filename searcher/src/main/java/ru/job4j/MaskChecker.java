package ru.job4j;

import java.nio.file.Path;

public class MaskChecker implements IChecker {

    private final String template;

    public MaskChecker(final String template) {
        this.template = template;
    }

    @Override
    public boolean check(Path file) {
        boolean result = false;
        if (file.getFileName().toString().endsWith(template)) {
            result = true;
        }
        return result;
    }
}
