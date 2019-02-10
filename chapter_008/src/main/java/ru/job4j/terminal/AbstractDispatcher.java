package ru.job4j.terminal;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractDispatcher implements IDispatcher<String> {
    public File dir;
    public final InputStream in;
    public final OutputStream out;
    public final DataInputStream din;
    public final DataOutputStream dout;
    public final static String SHOW_ALL = "ls";
    public final static String MOVE_UP = "cd..";
    public final static String MOVE_IN = "cd";
    public final static String DOWNLOAD = "download";
    public final static String UPLOAD = "upload";
    public final static String EXIT = "exit";
    public final static String HELP = "help";
    public final Map<String, Consumer<String>> map;

    public AbstractDispatcher(File dir, InputStream in, OutputStream out, DataInputStream din, DataOutputStream dout) {
        this.dir = dir;
        this.out = out;
        this.in = in;
        this.din = din;
        this.dout = dout;
        map = new HashMap<>();
    }

    @Override
    public void get(String ask) {
        String[] arr = ask.split(" ");
        if (arr.length == 1) {
            this.map.get(ask).accept(ask);
        } else {
            this.map.get(arr[0]).accept(ask);
        }
    }

    @Override
    public void loadTo(File file, OutputStream out) {
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] bt = new byte[1024];
            int length = in.read(bt);
            while (length > 0) {
                out.write(bt, 0, length);
                length = in.read(bt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFrom(File file, InputStream in, long size) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            byte[] bt = new byte[1024];
            int lenght;
            do {
                lenght = in.read(bt);
                out.write(bt, 0, lenght);
                out.flush();
            } while (file.length() < size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
