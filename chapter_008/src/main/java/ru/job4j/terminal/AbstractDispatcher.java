package ru.job4j.terminal;

import ru.job4j.terminal.IDispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractDispatcher implements IDispatcher<String> {
    public DataOutputStream out;
    public DataInputStream in;
    public final static String SHOW_ALL = "ls";
    public final static String MOVE_UP = "cd..";
    public final static String MOVE_IN = "cd";
    public final Map<String, Consumer<String>> tempalte;

    public AbstractDispatcher(DataOutputStream out, DataInputStream in) {
        this.out = out;
        this.in = in;
        tempalte = new HashMap<>();
    }

    @Override
    public void get(String ask) {
        String[] arr = ask.split(" ");
        if (arr.length == 1) {
            this.tempalte.get(ask).accept(ask);
        } else {
            this.tempalte.get(arr[0]).accept(ask);
        }
    }
}
