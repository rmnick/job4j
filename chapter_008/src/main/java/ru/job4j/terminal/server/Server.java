package ru.job4j.terminal.server;

import ru.job4j.terminal.Config;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final Socket socket;
    private File parent = new File(System.getProperty("user.dir"));


    public Server(final Socket socket) {
        this.socket = socket;
    }

    public void start() {
        String ask;
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             DataInputStream in = new DataInputStream(this.socket.getInputStream());
             DataOutputStream dout = new DataOutputStream(this.socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println(parent.getAbsolutePath());
            do {
                System.out.println("wait");
                while(!(ask = in.readUTF()).isEmpty()) {
                    System.out.println("in while");
                    System.out.println(ask);
                    dout.writeUTF(ask + " answer");
                    dout.writeUTF("");
                }
            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Config conf = new Config();
        try (Socket sc = new ServerSocket(Integer.parseInt(conf.get("port"))).accept()) {
            new Server(sc).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
