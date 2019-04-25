package ru.job4j.servlets.json;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class JsonStore {
    private Map<String, JsonUser> store = new ConcurrentHashMap<>();

    private JsonStore() {
    }

    public  static class JsonStoreHolder {
        public final static JsonStore INSTANCE = new JsonStore();
    }

    public static JsonStore getInstance() {
        return JsonStoreHolder.INSTANCE;
    }

    public JsonUser add(JsonUser user) {
        return store.put(user.getId(), user);
    }

    public JsonUser get(JsonUser user) {
        return store.get(user.getId());
    }

    public List<JsonUser> getAll() {
        return store.values().stream().collect(Collectors.toList());
    }
}
