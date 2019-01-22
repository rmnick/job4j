package ru.job4j.terminal.server;

import ru.job4j.terminal.Config;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Config conf = new Config();
        try (Socket socket = new ServerSocket(Integer.parseInt(conf.get("port"))).accept()) {
            new Terminal(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
