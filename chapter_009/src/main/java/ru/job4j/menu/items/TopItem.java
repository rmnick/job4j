package ru.job4j.menu.items;

import ru.job4j.menu.AbstractItem;
import ru.job4j.menu.IDemonstrator;

import java.util.List;

public class TopItem extends AbstractItem {
    public TopItem(String name, List<IDemonstrator> items) {
        super(name, items);
    }

    @Override
    public String toString() {
        return name;
    }

}
