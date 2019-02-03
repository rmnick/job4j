package ru.job4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * chose and return right checker according with template key;
 * -r, -f, -m
 */

public class CheckerDispatch {
    private final static String KEY_MASK = "-m";
    private final static String KEY_FULL_NAME = "-f";
    private final static String KEY_REGULAR = "-r";
    private final Map<String, Function<String, IChecker>> dispatch = new HashMap<>();


    public CheckerDispatch init() {
        this.load(KEY_MASK, this.mask());
        this.load(KEY_FULL_NAME, this.name());
        this.load(KEY_REGULAR, this.regular());
        return this;
    }

    private void load(String key, Function<String, IChecker> handle) {
        this.dispatch.put(key, handle);
    }

    private Function<String, IChecker> mask() {
        return template -> {
            return new MaskCheckerMaker().makeChecker(template);
        };
    }

    private Function<String, IChecker> regular() {
        return template -> {
            return new RegularCheckerMaker().makeChecker(template);
        };
    }

    private Function<String, IChecker> name() {
        return template -> {
            return new NameCheckerMaker().makeChecker(template);
        };
    }

    public IChecker get(String key, String template) {
        return this.dispatch.get(key).apply(template);
    }
}
