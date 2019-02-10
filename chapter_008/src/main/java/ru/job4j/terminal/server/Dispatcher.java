package ru.job4j.terminal.server;

import ru.job4j.terminal.AbstractDispatcher;

import java.io.*;
import java.util.function.Consumer;

public class Dispatcher extends AbstractDispatcher {

    public Dispatcher(File dir, InputStream in, OutputStream out, DataInputStream din, DataOutputStream dout) {
        super(dir, in, out, din, dout);
    }

    public Dispatcher init() {
        map.put(HELP, help());
        map.put(SHOW_ALL, show());
        map.put(MOVE_UP, up());
        map.put(MOVE_IN, down());
        map.put(DOWNLOAD, download());
        map.put(UPLOAD, upload());
        return this;
    }

    public Consumer<String> help() {
        return str -> {
          StringBuilder sb = new StringBuilder();
          String separator = System.lineSeparator();
          sb
                  .append("ls                              : show all files and folders in this directory")
                  .append(separator)
                  .append("cd \"absolute name\"              : move to specified directory")
                  .append(separator)
                  .append("cd..                            : move up to parent directory")
                  .append(separator)
                  .append("download \"absolute file name\"   : download specified file to current directory in the server from client")
                  .append(separator)
                  .append("upload \"file name\"              : upload file from server to special folder \"downloads\" in client")
                  .append(separator)
                  .append("help                            : show all command")
                  .append(separator)
                  .append("exit                            : exit from programme");
          try {
              dout.writeUTF(sb.toString());
              dout.writeUTF(dir.getAbsolutePath());
              dout.writeUTF("");
          } catch (IOException e) {
              e.printStackTrace();
          }
        };
    }

    public Consumer<String> show() {
        return str -> {
            try {
            String[] list = this.dir.list();
            for (String s : list) {
                dout.writeUTF(s);
            }
            dout.writeUTF(dir.getAbsolutePath());
            dout.writeUTF("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public Consumer<String> up() {
        return str -> {
            try {
                File parent = new File(dir.getParent());
                dir = parent;
                dout.writeUTF(dir.getAbsolutePath());
                dout.writeUTF("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public Consumer<String> down() {
        return str -> {
            try {
                String[] arr = str.split(" ");
                File child = new File(String.format("%s\\%s", dir.getAbsolutePath(), arr[1]));
                if (child.exists() && child.isDirectory()) {
                    dir = child;
                    dout.writeUTF(dir.getAbsolutePath());
                    dout.writeUTF("");
                } else {
                    dout.writeUTF("directory does not exist");
                    dout.writeUTF("");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public Consumer<String> download() {
        return str -> {
          try {
              String name = din.readUTF();
              long size = din.readLong();
              File file = new File(String.format("%s\\%s", dir.getAbsolutePath(), name));
              loadFrom(file, in, size);
              dout.writeUTF("download was successful");
              dout.writeUTF(dir.getAbsolutePath());
              dout.writeUTF("");
          } catch (IOException e) {
              e.printStackTrace();
          }
        };
    }

    public Consumer<String> upload() {
        return str -> {
          String[] arr = str.split(" ");
          try {
          File file = new File(String.format("%s\\%s", dir.getAbsolutePath(), arr[1]));
          if (file.exists()) {
              dout.writeUTF("exist");
              dout.writeLong(file.length());
              loadTo(file, out);
              if (din.readUTF().isEmpty()) {
                  dout.writeUTF("successful upload");
                  dout.writeUTF(dir.getAbsolutePath());
                  dout.writeUTF("");
              }
          } else {
              dout.writeUTF("file does not exist");
              dout.writeUTF(dir.getAbsolutePath());
              dout.writeUTF("");
          }
          } catch (IOException e) {
              e.printStackTrace();
          }
        };
    }
}
