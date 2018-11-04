package ru.job4j.unblock;

import java.util.concurrent.ConcurrentHashMap;

public class UnblockedCashe {
    private ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    private void update(Base model) throws OptimisticException {
        if (model.getVersion() != map.get(model.getId()).getVersion()) {
            throw new OptimisticException("model is already changed");
        }
        map.computeIfPresent(model.getId(), (key, value) -> {
            model.incVersion();
            value = model;
            return value;
        });
    }

    public void change(int id, String name) throws OptimisticException {
        Base model = map.get(id);
        model.setName(name);
        update(model);
    }

    public void add(Base model) {
        map.put(model.getId(), model);
    }

    public Base delete(Base model) {
        return map.remove(model.getId());
    }
}
