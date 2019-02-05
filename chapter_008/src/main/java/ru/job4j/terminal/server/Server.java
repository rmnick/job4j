package ru.job4j.terminal.server;

import ru.job4j.terminal.Config;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
             DataInputStream in = new DataInputStream(this.socket.getInputStream())) {
            System.out.println(parent.getAbsolutePath());
            do {
                System.out.println("in while");
                out.println(parent.getAbsolutePath());
                ask = in.readUTF();
                System.out.println(ask);
                out.println(ask + "i answer");
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
