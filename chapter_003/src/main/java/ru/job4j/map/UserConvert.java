package ru.job4j.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserConvert {
    /*
    public Map<Integer, User> convertToHashMap(List<User> ru.job4j.list) {
        Map<Integer, User> ru.job4j.map = new HashMap<>();
        for (User user : ru.job4j.list) {
            ru.job4j.map.put(user.getId(), user);
        }
        return ru.job4j.map;
    }
    */
    public Map<Integer, User> convertToHashMap(List<User> list) {
        return list.stream()
                .collect(Collectors.toMap(user -> user.getId(), Function.identity()));
    }
}
