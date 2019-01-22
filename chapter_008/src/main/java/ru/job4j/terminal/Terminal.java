package ru.job4j.terminal;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.Socket;

public class Terminal {
    private Socket socket;
    private final String[] command = {"help", "ls", "cd", "cd..", "load", "upload"};
    private File parent = new File("chapter_008/");

    public Terminal(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        String ask;
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream();
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(out), true);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            do {
                System.out.println(parent.getAbsolutePath());
                pw.println(parent.getAbsolutePath());
                ask = br.readLine();
                System.out.println(ask);
                if (validate(ask, pw)) {
                    String[] str = parse(ask);
                    System.out.println("valid");
                    System.out.println(ask);
                    switch (str[0]) {
                        case "ls":
                            System.out.println("case ls");
                            show(pw);
                            break;
                        case "cd..":
                            System.out.println("case cd..");
                            goOneFolderUp(pw);
                            break;
                        case "cd":
                            System.out.println("case cd");
                            changeDirectory(ask, pw);
                            break;
                            default:
                                break;

                    }
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show(PrintWriter pw) {
        System.out.println("in Show" + parent.list().length);
        String[] str = parent.list();
        for (String s : str) {
            System.out.println(s);
            pw.println(s);
            }
        pw.println();
    }

    public void changeDirectory(String string, PrintWriter pw) {
        String[] str = string.split(" ");
        String name = parent.getAbsolutePath() + "/" + str[1] + "/";
        System.out.println(name);
        File dir = new File(name);
        if (dir.exists() && dir.isDirectory()) {
            parent = dir;
            //pw.println(dir.getAbsolutePath());
            pw.println();
        } else {
            pw.println("directory doesn't exist");
            pw.println();
        }
    }

    public void goOneFolderUp(PrintWriter pw) {
        System.out.println("in folderUp");
        if (parent.getParentFile() == null) {
            pw.println("parent catalog");
            pw.println();
        } else {
            pw.println(parent.getAbsolutePath());
            pw.println();
        }
    }

    public boolean validate(String string, PrintWriter pw) {
        String[] str = string.split(" ");
        boolean result = false;
            if (str != null) {
                for (String s : command) {
                    if (str[0].equals(s)) {
                        result = true;
                        break;
                    }
                }
            }
            if (!result) {
                pw.println("wrong command");
                pw.println();
            }
        return result;
    }

    public String[] parse(String str) {
        return str.split(" ");
    }
}
