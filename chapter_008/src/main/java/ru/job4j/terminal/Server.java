package ru.job4j.terminal;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final Socket socket;

    public Server(final Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        Config conf = new Config();
        try (Socket socket = new ServerSocket(Integer.parseInt(conf.get("port"))).accept()) {
            new Terminal(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
