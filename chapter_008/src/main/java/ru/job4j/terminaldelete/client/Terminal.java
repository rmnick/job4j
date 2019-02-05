package ru.job4j.terminaldelete.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Terminal {
    private Socket socket;
    private final String downloads;

    public Terminal(Socket socket, String downloads) {
        this.socket = socket;
        this.downloads = downloads;
    }

    public void start() {
        File dir = new File(downloads);
        System.out.println(dir.getAbsolutePath());
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream();
             PrintWriter pw = new PrintWriter(out, true);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            Scanner console = new Scanner(System.in);
            String str;
            String ask;
            do {
                System.out.println(br.readLine());
                ask = console.nextLine();
                pw.println(ask);
                str = br.readLine();
                while (str != null && !str.isEmpty()) {
                    System.out.println(str);
                    //upload from server
                    if (str.equals("upload")) {
                        String name = br.readLine();
                        long size = Long.parseLong(br.readLine());
                        upload(in, name, size);
                        //download on server
                    } else if (str.equals("download")) {
                        String[] s = ask.split(" ");
                        File file = new File(s[1]);
                        //if file exist send name to server and start downloading
                        if (file.exists()) {
                            System.out.println("!!!!!exist");
                            System.out.println(file.getName());
                            pw.println(file.getName());
                            pw.println(file.length());
                            download(file, out);
                        } else {
                            pw.println("file doesn't exist");
                        }
                    }
                    str = br.readLine();
                }
            } while (!str.equals("exit"));
            console.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upload(InputStream in, String file, long size) {
        File f  = new File(downloads + "/" + file);
        try (OutputStream out = new FileOutputStream(f)) {
            while (f.length() < size) {
                out.write(in.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void download(File file, OutputStream out) {
        try (InputStream in = new FileInputStream(file)) {
            byte[] buf = new byte[4096];
            int count;
            while ((count = in.read(buf)) > 0) {
                out.write(buf, 0, count);
            }
            out.flush();
            System.out.println("end buf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
