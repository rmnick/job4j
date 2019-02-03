package ru.job4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * just started...
 */
public class Validator {
    private static final String DIRECTORY = "-d";
    private static final String TEMPLATE = "-n";
    private static final String KEY_FULL_NAME = "-f";
    private static final String KEY_REGULAR = "-r";
    private static final String KEY_MASK = "-m";
    private static final String DIRECTORY_LOG = "-o";
    private Map<String, String> map = new HashMap<>();

    public Validator init() {
        return this;
    }

    public void load(String key, String value, BiFunction<String, String, String> validateResult) {
        this.map.put(key, validateResult.apply(key, value));
    }

    private BiFunction<String, String, String> validateDirectory() {
        return (key, value) -> {
            String result = null;
            if (key != null && value != null && key.equals("-d")) {
                File dir = new File(value);
                if (dir.exists() && dir.isDirectory()) {
                    result = value;
                }
            }
          return result;
        };
    }
}
