package ru.job4j.simplegenerator;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleGenerator {

    /**
     * combine all methods of the class to replace all keys from string
     * split string to array
     * check that string contains unnecessary keys
     * check that map for unnecessary keys
     * replace all
     * @param example String
     * @param map Map<String, String>
     * @return String
     */
    public String fixString(String example, Map<String, String> map) {
        String result;
        String[] arr = example.split(" ");
        Map<String, Integer> stringMap = this.stringToMap(arr);
        checkString(stringMap, map);
        result = replaceKey(example, stringMap.keySet(), map);
        return result;
    }

    /**
     * replace some words in "example" according to this pattern("${}"), use "stringKeys" as keys for map<key, value>
     * @param example String
     * @param stringKeys Set<String>
     * @param map Map<String, String>
     * @return String
     */
    public String replaceKey(String example, Set<String> stringKeys, Map<String, String> map) {
        for (String str : stringKeys) {
            example = example.replaceFirst(String.format("(\\$\\{%s\\})", str), this.exchange(str, map));
        }
        return example;
    }

    /**
     * get value from a map of str as key. Throw custom exception if key doesn't exist
     * @param str String
     * @param map Map<String, String>
     * @return String
     * @throws KeyException RuntimeException
     */
    public String exchange(String str, Map<String, String> map) throws KeyException {
        String result = map.get(str);
        if (result == null) {
            throw new KeyException(String.format("the map doesn't contain %s", str));
        }
        return result;
    }

    /**
     * check that string for keys from map. throw exception if map contains unnecessary key
     * @param stringMap HashMap<String, Integer>
     * @param map HashMap<String, String>
     * @throws KeyException RuntimeException
     */
    public void checkString(Map<String, Integer> stringMap, Map<String, String> map) throws KeyException {
        map.keySet().forEach(key -> {
            if (stringMap.get(key) == null) {
                throw new KeyException(String.format("the string doesn't contain %s", key));
            }
        });
    }

    /**
     * create Map with unique keys from string
     * use association key-value(string = key; value = dummy) from this map in checkString method
     * @param arr String[]
     * @return Map<String, Integer>
     */
    public Map<String, Integer> stringToMap(String[] arr) {
        Map<String, Integer> result;
        result = Arrays.stream(arr)
                .filter(str -> str.startsWith("${") && str.contains("}"))
                .map(str -> str.substring(str.indexOf("{") + 1, str.indexOf("}")))
                .collect(Collectors.toMap(str -> str, value -> 1));
        return result;
    }
}
