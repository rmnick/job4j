package ru.job4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Validator {
    private static final String DIRECTORY = "-d";
    private static final String TEMPLATE = "-n";
    private static final String KEY_FULL_NAME = "-f";
    private static final String KEY_REGULAR = "-r";
    private static final String KEY_MASK = "-m";
    private static final String DIRECTORY_LOG = "-o";
    private Map<Integer, Function<String, Boolean>> map = new HashMap<>();
    private Map<Integer, String> store = new HashMap<>();
    private final String[] args;

    public Validator(String[] args) {
        this.args = args;
    }

    private void init() {
        this.load(0, validateKeyOfDirectory());
        this.load(1, validateDirectory());
        this.load(2, validateKeyOfTemplateName());
        this.load(3, validateTemplate());
        this.load(4, validateKeyTemplate());
        this.load(5, validateKeyOfLogDirectory());
        this.load(6, validateDirectoryOfLog());
    }

    private void load(Integer position, Function<String, Boolean> validateResult) {
        this.map.put(position, validateResult);
    }

    private Function<String, Boolean> validateKeyOfDirectory() {
        return arg -> {
            Boolean result = false;
            if (arg != null && arg.equals(DIRECTORY)) {
                result = true;
                store.put(1, arg);
            } else {
                System.out.println("please enter correct key \"-d\" ");
            }
            return result;
        };
    }

    private Function<String, Boolean> validateDirectory() {
        return arg -> {
            Boolean result = false;
            if (arg != null) {
                File dir = new File(arg);
                if (dir.exists() && dir.isDirectory()) {
                    result = true;
                    store.put(2, arg);
                } else {
                    System.out.println("please enter correct directory for search. that directory does not exist");
                }
            }
          return result;
        };
    }

    private Function<String, Boolean> validateKeyOfTemplateName() {
        return arg -> {
            Boolean result = false;
            if (arg != null && arg.equals(TEMPLATE)) {
                result = true;
                store.put(3, arg);
            } else {
                System.out.println("please enter correct key \"-n\"");
            }
            return result;
        };
    }

    private Function<String, Boolean> validateTemplate() {
        return arg -> {
            Boolean result = false;
            if (arg != null) {
                result = true;
                store.put(4, arg);
            } else {
                System.out.println("please enter a template for search");
            }
            return result;
        };
    }

    private Function<String, Boolean> validateKeyTemplate() {
        return arg -> {
            Boolean result = false;
            if (arg != null && (arg.equals(KEY_FULL_NAME) || arg.equals(KEY_MASK) || arg.equals(KEY_REGULAR))) {
                result = true;
                store.put(5, arg);
            } else {
                System.out.println("please enter correct keys \"-f or -r or -m\"");
            }
            return result;
        };
    }

    private Function<String, Boolean> validateKeyOfLogDirectory() {
        return arg -> {
            Boolean result = false;
            if (arg != null && arg.equals(DIRECTORY_LOG)) {
                result = true;
                store.put(6, arg);
            } else {
                System.out.println("please enter correct key \"-o\"");
            }
            return result;
        };
    }

    private Function<String, Boolean> validateDirectoryOfLog() {
        return arg -> {
            Boolean result = false;
            if (arg != null) {
                File dir = new File(arg);
                if (dir.exists() && dir.isDirectory()) {
                    result = true;
                    store.put(7, arg);
                } else {
                    System.out.println("please enter correct log directory. that directory does not exist");
                }
            }
            return result;
        };
    }

    public boolean validate() {
        this.init();
        boolean result = true;
        if (args.length != 7) {
            result = false;
            System.out.println("please enter all keys");
        } else {
            for (int i = 0; i < args.length; i++) {
                if (!map.get(i).apply(args[i])) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public String getDirectory() {
        return store.get(2);
    }

    public String getTemplate() {
        return store.get(4);
    }

    public String getKeyTemplate() {
        return store.get(5);
    }

    public String getDirectoryLog() {
        return store.get(7);
    }
}
