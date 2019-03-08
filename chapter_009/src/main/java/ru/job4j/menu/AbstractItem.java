package ru.job4j.menu;

import java.util.List;

public abstract class AbstractItem implements IDemonstrator {
    protected final String name;
    protected final List<IDemonstrator> items;

    public AbstractItem(final String name, final List<IDemonstrator> items) {
        this.name = name;
        this.items = items;
    }

    public void show() {
        System.out.println(this.toString());
        if (!items.isEmpty()) {
            items.forEach(item -> {
                item.show();
            });
        }
    }
}
