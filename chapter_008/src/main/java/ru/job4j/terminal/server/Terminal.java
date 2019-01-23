package ru.job4j.terminal.server;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.Socket;

public class Terminal {
    private Socket socket;
    private final String[] command = {"help", "ls", "cd", "cd..", "download", "upload"};
    private File parent = new File(System.getProperty("user.dir"));

    public Terminal(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        String ask;
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream();
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(out), true);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            do {
                System.out.println(parent.getAbsolutePath());
                pw.println(parent.getAbsolutePath());
                ask = br.readLine();
                System.out.println(ask);
                //validate command string and parse it on command
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
                        case "upload":
                            System.out.println("case upload");
                            upload(str[1], out, pw);
                            break;
                        case "download":
                            System.out.println("case download");
                            pw.println("download");
                            String s = br.readLine();
                            if (s.equals("file doesn't exist")) {
                                pw.println(s);
                                pw.println();
                            } else {
                                System.out.println("!!!! in download");
                                //s = br.readLine();
                                System.out.println("print name" + s);
                                long size = Long.parseLong(br.readLine());
                                System.out.println("print size" + size);
                                download(s, in, size, pw);
                            }
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

    /**
     * ls
     * @param pw
     */
    public void show(PrintWriter pw) {
        String[] str = parent.list();
        for (String s : str) {
            System.out.println(s);
            pw.println(s);
            }
        pw.println();
    }

    /**
     * cd "directory"
     * @param string
     * @param pw
     */
    public void changeDirectory(String string, PrintWriter pw) {
        String[] str = string.split(" ");
        String name = parent.getAbsolutePath() + "/" + str[1] + "/";
        System.out.println(name);
        File dir = new File(name);
        if (dir.exists() && dir.isDirectory()) {
            parent = dir;
            pw.println();
        } else {
            pw.println("directory doesn't exist");
            pw.println();
        }
    }

    /**
     * cd..
     * @param pw
     */
    public void goOneFolderUp(PrintWriter pw) {
        parent = new File(parent.getAbsolutePath());
        if (parent.getParentFile() == null) {
            pw.println("parent catalog");
            pw.println();
        } else {
            parent = parent.getParentFile();
            System.out.println(parent.getAbsolutePath());
            pw.println();
        }
    }

    /**
     * !!!!!!!!!!
     * @param name
     * @param out
     * @param pw
     */
    public void upload(String name, OutputStream out, PrintWriter pw) {
        File file = new File(parent.getAbsoluteFile() + "/" + name);
        if (file.exists()) {
            pw.println("upload");
            pw.println(file.getName());
            System.out.println(file.length());
            pw.println(file.length());
            try (InputStream in = new FileInputStream(file)) {
                byte[] buf = new byte[4096];
                int count;
                while ((count = in.read(buf)) > 0) {
                    out.write(buf, 0, count);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.println("successful");
            pw.println();
        } else {
            pw.println("file doesn't exist");
            pw.println();
        }
    }

    /**
     * !!!!!!!!!!!
     * @param name
     * @param in
     * @param size
     * @param pw
     */
    public void download(String name, InputStream in, long size, PrintWriter pw) {
        File file = new File(parent.getAbsolutePath() + "/" + name);
        System.out.println(file.getAbsolutePath());
        try (OutputStream out = new FileOutputStream(file)) {
            System.out.println(file.length());
            System.out.println(size);
            byte[] buf = new byte[4096];
            int count;
            int a = 0;
            while (file.length() < size) {
                out.write(in.read());
            }
            System.out.println("!!!freeeee");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("successful");
        pw.println();
    }

    public boolean validate(String string, PrintWriter pw) {
        String[] str = string.split(" ");
        boolean result = false;
            if (str != null && str.length < 3) {
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
