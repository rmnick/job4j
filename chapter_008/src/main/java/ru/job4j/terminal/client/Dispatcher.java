package ru.job4j.terminal.client;

import ru.job4j.terminal.AbstractDispatcher;

import java.io.*;
import java.util.function.Consumer;

public class Dispatcher extends AbstractDispatcher {

    public Dispatcher(File dir, InputStream in, OutputStream out, DataInputStream din, DataOutputStream dout) {
        super(dir, in, out, din, dout);
    }

    public Dispatcher init() {
        map.put(SHOW_ALL, talk());
        map.put(MOVE_UP, talk());
        map.put(MOVE_IN, talk());
        map.put(DOWNLOAD, download());
        map.put(UPLOAD, upload());
        map.put(HELP, talk());
        map.put(EXIT, exit());
        return this;
    }

    /**
     * method for conversation with server
     * send command from client input and take an answer
     * use method ask()
     * @return
     */
    public Consumer<String> talk() {
        return str -> {
          try {
              dout.writeUTF(str);
              ask();
          } catch (IOException e) {
              e.printStackTrace();
          }
        };
    }

    /**
     * exit from programme
     * @return
     */
    public Consumer<String> exit() {
        return str -> {
          try {
              dout.writeUTF(str);
          } catch (IOException e) {
              e.printStackTrace();
          }
        };
    }

    /**
     * downloading file from client PC to server in current directory
     * client must enter "download" and absolute name of file
     * @return
     */
    public Consumer<String> download() {
        return str -> {
            String[] arr = str.split(" ");
          try {
              File file = new File(arr[1]);
              if (file.exists()) {
                  dout.writeUTF(str);
                  dout.writeUTF(file.getName());
                  dout.writeLong(file.length());
                  loadTo(file, out);
                  ask();
              } else {
                  System.out.println("file does not exist");
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
        };
    }

    /**
     * uploading file from server(current directory) to client PC in special folder(downloads)
     * client must enter "upload" and name of file
     * @return
     */
    public Consumer<String> upload() {
        return str -> {
            String[] arr = str.split(" ");
            try {
                dout.writeUTF(str);
                if (din.readUTF().equals("exist")) {
                    File file = new File(String.format("%s\\%s", dir.getAbsoluteFile(), arr[1]));
                    long size = din.readLong();
                    loadFrom(file, in, size);
                    dout.writeUTF("");
                    ask();
                } else {
                    ask();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * asking server and print the answers
     * @throws IOException
     */
    public void ask() throws IOException {
        String ask;
        ask = din.readUTF();
        while (!ask.isEmpty()) {
            System.out.println(ask);
            ask = din.readUTF();
        }
    }
}
