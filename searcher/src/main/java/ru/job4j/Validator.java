package ru.job4j;

import java.io.File;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Map;
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
    private final String[] args;

    public Validator(String[] args) {
        this.args = args;
    }

    private void init() {
        this.load(DIRECTORY, validateDirectory(), this.getValue(DIRECTORY));
        this.load(TEMPLATE, validateTemplate(), this.getValue(TEMPLATE));
        this.load(KEY_FULL_NAME, validateKeyTemplate(), KEY_FULL_NAME);
        this.load(KEY_REGULAR, validateKeyTemplate(), KEY_REGULAR);
        this.load(KEY_MASK, validateKeyTemplate(), KEY_MASK);
        this.load(DIRECTORY_LOG, validateDirectory(), this.getValue(DIRECTORY_LOG));
    }

    private void load(String key, Function<String, String> validateResult, String value) {
        this.map.put(key, validateResult.apply(value));
    }

    private Function<String, String> validateDirectory() {
        return value -> {
            String result = null;
            if (value != null) {
                File dir = new File(value);
                if (dir.exists() && dir.isDirectory()) {
                    result = value;
                }
            }
          return result;
        };
    }

    private Function<String, String> validateTemplate() {
        return value -> {
            String result = null;
            if (value != null) {
                result = value;
            }
            return result;
        };
    }

    private Function<String, String> validateKeyTemplate() {
        return key -> {
            return key;
        };
    }

    private String getValue(String key) {
        String result = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(key)) {
                result = args[i + 1];
            }
        }
        return result;
    }

    private String getKeyTemplate() {
        return "";
    }

    public boolean validate() {
        this.init();
        boolean result = true;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (map.get(args[i]) == null) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public String getDirectory() {
        return map.get(DIRECTORY);
    }

    public String getTemplate() {
        return map.get(TEMPLATE);
    }

    public String getDirectoryLog() {
        return map.get(DIRECTORY_LOG);
    }


}
