package ru.job4j;

import java.nio.file.Path;

public class NameCheckerMaker implements ICheckerMaker {

    private Path file;
    private final String template;

    public NameCheckerMaker(final Path file, final String template) {
        this.file = file;
        this.template = template;
    }

    @Override
    public IChecker makeChecker() {
        return new NameChecker(file, template);
    }
}
