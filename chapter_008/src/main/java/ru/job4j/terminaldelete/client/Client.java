package ru.job4j.terminaldelete.client;

import ru.job4j.terminaldelete.Config;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Config config = new Config();
        try (Socket socket = new Socket(InetAddress.getByName(config.get("ip")), Integer.parseInt(config.get("port")))) {
            new Terminal(socket, config.get("downloads")).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
