package ru.job4j;

import java.nio.file.Path;

public class RegularCheckerMaker implements ICheckerMaker {

    @Override
    public IChecker makeChecker(String template) {
        return new RegularChecker(template);
    }
}
