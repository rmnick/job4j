package ru.job4j.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserConvert {
    /*
    public Map<Integer, User> convertToHashMap(List<User> list) {
        Map<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
    */
    public Map<Integer, User> convertToHashMap(List<User> list) {
        return list.stream()
                .collect(Collectors.toMap(user -> user.getId(), Function.identity()));
    }
}
