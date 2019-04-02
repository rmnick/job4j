package ru.job4j.foodstorage.storage;

public abstract class AbstractDecoratorStorage extends AbstractStorage {
    public final AbstractStorage store;

    public AbstractDecoratorStorage(String name, final AbstractStorage store) {
        super(name);
        this.store = store;
    }
}
