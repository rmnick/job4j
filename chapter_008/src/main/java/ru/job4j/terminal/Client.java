package ru.job4j.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Scanner console = new Scanner(System.in);
            String str;
            String ask;
            do {
                System.out.println(in.readLine());
                ask = console.nextLine();
                out.println(ask);
                str = in.readLine();
                while (str != null && !str.isEmpty()) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (!ask.equals("exit"));
            console.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Config config = new Config();
        try (Socket socket = new Socket(InetAddress.getByName(config.get("ip")), Integer.parseInt(config.get("port")))) {
            new Client(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
