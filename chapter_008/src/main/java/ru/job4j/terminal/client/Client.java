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
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             DataInputStream in = new DataInputStream(this.socket.getInputStream());
             DataOutputStream dout = new DataOutputStream(this.socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            Scanner console = new Scanner(System.in);
            String input;
            String ask;
            //out.println("-help");
            System.out.println("before while");
            while (true) {
                ask = console.nextLine();
                dout.writeUTF(ask);
                while (!(input = in.readUTF()).isEmpty()) {
                    //System.out.println("in while");
                    System.out.println(input);
                    //input = in.readUTF();
                }
            }
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
