package ru.job4j.terminal;

import java.util.function.Consumer;

public interface IDispatcher<T> {
    public IDispatcher<T> init();
    public Consumer<T> show();
    public void get(String str);
}
