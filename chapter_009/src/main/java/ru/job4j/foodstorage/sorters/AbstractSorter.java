package ru.job4j.foodstorage.sorters;

import ru.job4j.foodstorage.storage.IStorage;

public abstract class AbstractSorter implements ISorter {
    protected final IStorage IStorage;

    public AbstractSorter(final IStorage IStorage) {
        this.IStorage = IStorage;
    }
}
