package ru.job4j;

public class NameCheckerMaker implements ICheckerMaker {

    @Override
    public IChecker makeChecker(String template) {
        return new NameChecker(template);
    }
}
