package ru.job4j.terminal;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final Socket socket;

    public Server(final Socket socket) {
        this.socket = socket;
    }

    public void start() {
        File parent = new File("chapter_008/");
        try (InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        PrintWriter pw = new PrintWriter((new OutputStreamWriter(out)), true)) {
            String ask;
            pw.println(parent.getName());
            do {
                System.out.println("wait command ...");
                ask = br.readLine();
                System.out.println(ask);
                /*if (ask.equals("ls")) {
                    String[] names = parent.list();
                    for (String str : names) {
                        pw.println(str);
                    }
                    pw.println();
                }*/
                //select(ask, parent, out);
            } while (!"exit".equals(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void select(String string, File parent, OutputStream out) {
        String result = null;
        String[] str = string.split(" ");
        String[] names;
        try (PrintWriter pw = new PrintWriter((new OutputStreamWriter(out)), true)) {
            switch (str[0]) {
                case "ls":
                    names = parent.list();
                    for (String s : names) {
                        pw.println(s);
                    }
                    pw.println();
                    break;
                case "cd":
                    File file;
                    if (str[1].equals("..")) {
                        file = parent.getParentFile();
                        pw.println(file.getName());
                        parent = file;
                    } else {
                        file = parent;
                        System.out.println(str[1]);
                        names = parent.list();
                        for (String s : names) {
                            if (str[1].equals(s)) {
                                System.out.println("!!!!!!!!!");
                                pw.println(file.getName() + s);
                            }
                        }
                    }
                    break;
                    default:
                        break;
            }
        }

    }

    public static void main(String[] args) {
        Config conf = new Config();
        try (Socket socket = new ServerSocket(Integer.parseInt(conf.get("port"))).accept()) {
            new Server(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
