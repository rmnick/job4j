package ru.job4j.terminal.server;

import ru.job4j.terminal.Config;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final Socket socket;

    public Server(final Socket socket) {
        this.socket = socket;
    }

    public void start() {
        File parent = new File(System.getProperty("user.dir"));
        String ask;
        try (DataInputStream in = new DataInputStream(this.socket.getInputStream());
             DataOutputStream out = new DataOutputStream(this.socket.getOutputStream())) {
            System.out.println(parent.getAbsolutePath());
            Dispatcher dis = new Dispatcher(parent, out, in);
            dis.init();
            out.writeUTF(parent.getAbsolutePath());
            do {
                System.out.println("wait");
                ask = in.readUTF();
                while (!ask.isEmpty()) {
                    out.writeUTF(ask + " show");
                    dis.get(ask);
                    ask = in.readUTF();
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
