package ru.job4j.terminal.client;

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
                    if (str.equals("upload")) {
                        String name = br.readLine();
                        upload(in, name);
                    }
                    str = br.readLine();
                }
            } while (!ask.equals("exit"));
            console.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upload(InputStream in, String file) {
        try (OutputStream out = new FileOutputStream(new File(downloads + "/" + file))) {
            byte[] buf = new byte[4096];
            int count;
            while ((count = in.read(buf)) > 0) {
                out.write(buf, 0, count);
            }
            System.out.println("im out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
