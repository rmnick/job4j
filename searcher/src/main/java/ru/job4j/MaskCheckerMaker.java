package ru.job4j;

public class MaskCheckerMaker implements ICheckerMaker {

    private final String template;

    public MaskCheckerMaker(final String template) {
        this.template = template;
    }

    @Override
    public IChecker makeChecker() {
        return new MaskChecker(template);
    }
}
