package ru.job4j.terminal;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface IDispatcher<T> {
    IDispatcher<T> init();
    void get(String str);
    void loadTo(File file, OutputStream out);
    void loadFrom(File file, InputStream in, long size);
}
