package ru.job4j.simplegenerator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExSimpleGenerator {
    private static final Pattern PATTERN = Pattern.compile("\\$\\{\\w+\\}");

    public String fixString(String str, Map<String, String> map) {
        unnecessaryKeyMapCheck(str, map);
        return replace(str, map);
    }

    /**
     * replace all matches
     * if string contains extra key throw KeyException
     * @param example String
     * @param map Map<String, String>
     * @return String
     * @throws KeyException RuntimeException
     */
    public String replace(String example, Map<String, String> map) throws KeyException {
        Matcher matcher = PATTERN.matcher(example);
        while (matcher.find()) {
            String key = matcher.group().substring(2, matcher.group().length() - 1);
            String value = map.get(key);
            if (value == null) {
                throw new KeyException(String.format("the map doesn't contain %s", key));
            }
            example = matcher.replaceFirst(value);
            matcher = PATTERN.matcher(example);
        }
        return example;
    }

    /**
     * checking for the map contains unnecessary key
     * if that's true throw exception
     * @param example String
     * @param map Nap<String, String>
     */
    public void unnecessaryKeyMapCheck(String example, Map<String, String> map) throws KeyException {
        Map<String, Integer> strMap = new HashMap<>();
        Matcher matcher = PATTERN.matcher(example);
        while (matcher.find()) {
            String key = matcher.group().substring(2, matcher.group().length() - 1);
            strMap.put(key, 1);
        }
        map.keySet().forEach(key -> {
            if (strMap.get(key) == null) {
                throw new KeyException(String.format("the string doesn't contain %s", key));
            }
        });
    }
}
