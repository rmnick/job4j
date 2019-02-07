package ru.job4j.terminal.client;

import ru.job4j.terminal.Config;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final String downloads;

    public Client(final Socket socket, final String downloads) {
        this.socket = socket;
        this.downloads = downloads;
    }

    public void start() {
        try (DataInputStream in = new DataInputStream(this.socket.getInputStream());
             DataOutputStream out = new DataOutputStream(this.socket.getOutputStream())) {
            Scanner console = new Scanner(System.in);
            String ask;
            Validator validator = new Validator();
            Dispatcher dispatcher = new Dispatcher(out, in);
            dispatcher.init();
            System.out.println(in.readUTF());
            do {
                ask = console.nextLine();
                if (validator.validate(ask)) {
                    dispatcher.get(ask);
                }
            } while (!ask.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] ags) {
        Config config = new Config();
        try (Socket sc = new Socket(InetAddress.getByName(config.get("ip")), Integer.parseInt(config.get("port")))) {
            new Client(sc, config.get("downloads")).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
