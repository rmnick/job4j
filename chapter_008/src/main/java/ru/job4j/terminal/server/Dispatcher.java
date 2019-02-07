package ru.job4j.terminal.server;

import ru.job4j.terminal.AbstractDispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class Dispatcher extends AbstractDispatcher {
    private File parent;

    public Dispatcher(File parent, DataOutputStream out, DataInputStream in) {
        super(out, in);
        this.parent = parent;
    }

    public Dispatcher init() {
        tempalte.put(AbstractDispatcher.SHOW_ALL, show());
        this.tempalte.put(MOVE_UP, up());
        this.tempalte.put(MOVE_IN, down());
        return this;
    }

    public Consumer<String> show() {
        return str -> {
            try {
            String[] list = this.parent.list();
            for (String s : list) {
                out.writeUTF(s);
            }
            out.writeUTF(parent.getAbsolutePath());
            out.writeUTF("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public Consumer<String> up() {
        return str -> {
            try {
                File dir = new File(parent.getParent());
                parent = dir;
                out.writeUTF(parent.getAbsolutePath());
                out.writeUTF("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public Consumer<String> down() {
        return str -> {
            try {
                File dir = new File(parent.getAbsolutePath() + "/" + str);
                parent = dir;
                out.writeUTF(parent.getAbsolutePath());
                out.writeUTF("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
