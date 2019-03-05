package ru.job4j.foodstorage.sorters;

import ru.job4j.foodstorage.storage.Storage;

public abstract class AbstractSorter implements ISorter {
    protected final Storage storage;

    public AbstractSorter(final Storage storage) {
        this.storage = storage;
    }
}
