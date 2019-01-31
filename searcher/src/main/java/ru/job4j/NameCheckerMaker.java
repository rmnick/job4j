package ru.job4j;

import java.nio.file.Path;

public class NameCheckerMaker implements ICheckerMaker {

    @Override
    public IChecker makeChecker(String template) {
        return new NameChecker(template);
    }
}
