package ru.job4j.terminal.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Dispatcher {
    private File parent;
    private DataOutputStream out;
    private final String SHOW_ALL = "ls";
    private final String MOVE_UP = "cd..";
    private final String MOVE_IN = "cd";
    private final Map<String, Consumer<String>> tempalte = new HashMap<>();

    public Dispatcher(File parent, DataOutputStream out) {
        this.parent = parent;
        this.out = out;
    }

    public Dispatcher init() {
        this.tempalte.put(SHOW_ALL, show());
        return this;
    }

    private Consumer<String> show() {
        return str -> {
            try {
            String[] list = this.parent.list();
            for (String s : list) {
                System.out.println(s);
                out.writeUTF(s);
            }
            out.writeUTF("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public void get(String ask) {
        this.tempalte.get(ask).accept(ask);
    }


}
