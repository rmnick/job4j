package ru.job4j.terminal.client;

import ru.job4j.terminal.AbstractDispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class Dispatcher extends AbstractDispatcher {

    public Dispatcher(DataOutputStream out, DataInputStream in) {
        super(out, in);
    }

    public Dispatcher init() {
        this.tempalte.put(SHOW_ALL, show());
        return this;
    }

    public Consumer<String> show() {
        return str -> {
            String input;
          try {
              out.writeUTF(str);
              input = in.readUTF();
              while (!input.isEmpty()) {
                  System.out.println(input);
                  input = in.readUTF();
              }
              out.writeUTF("");
          } catch (IOException e) {
              e.printStackTrace();
          }
        };
    }
}
