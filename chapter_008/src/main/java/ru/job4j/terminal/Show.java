package ru.job4j.terminal;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Show {
    private final Socket socket;
    private final String string;

    public Show(final Socket socket, final String string) {
        this.socket = socket;
        this.string = string;
    }

    public void execute() {
        File dir =  new File(string);
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
            String[] str = dir.list();
            for (String s : str) {
                out.println(s);
            }
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
