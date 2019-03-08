package ru.job4j.menu.items;

import ru.job4j.menu.AbstractItem;
import ru.job4j.menu.IDemonstrator;
import ru.job4j.menu.IExecutor;

import java.util.List;

public class SecondItem extends AbstractItem implements IExecutor {
    public SecondItem(String name, List<IDemonstrator> items) {
        super(name, items);
    }

    @Override
    public String toString() {
        return String.format("------%s", name);
    }

    @Override
    public void execute() {

    }
}
