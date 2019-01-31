package ru.job4j;

public class MaskCheckerMaker implements ICheckerMaker {

    @Override
    public IChecker makeChecker(String template) {
        return new MaskChecker(template);
    }
}
