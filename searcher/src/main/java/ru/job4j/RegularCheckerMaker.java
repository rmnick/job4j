package ru.job4j;

import java.nio.file.Path;

public class RegularCheckerMaker implements ICheckerMaker {

    private Path file;
    private final String template;

    public RegularCheckerMaker(final Path file, final String template) {
        this.file = file;
        this.template = template;
    }

    @Override
    public IChecker makeChecker() {
        return new RegularChecker(file, template);
    }
}
