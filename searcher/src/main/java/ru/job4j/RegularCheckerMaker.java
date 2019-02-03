package ru.job4j;

public class RegularCheckerMaker implements ICheckerMaker {

    @Override
    public IChecker makeChecker(String template) {
        return new RegularChecker(template);
    }
}
