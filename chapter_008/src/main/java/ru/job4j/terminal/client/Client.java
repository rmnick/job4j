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

    /**
     * make downloads folder on client PC
     * this folder for downloading files from server
     * @return
     */
    public File mkDir() {
        File dir = new File(downloads);
        dir.mkdir();
        return dir;
    }

    /**
     * main method on client side
     * create Validator for validation our input
     * create Dispatcher for dispatch calculation according with user's commands
     * start conversation with server
     */
    public void start() {
        try (InputStream in = this.socket.getInputStream();
             OutputStream out = this.socket.getOutputStream();
             DataOutputStream dout = new DataOutputStream(out);
             DataInputStream din = new DataInputStream(in)) {
            Scanner console = new Scanner(System.in);
            String ask;
            Validator validator = new Validator();
            Dispatcher dispatcher = new Dispatcher(mkDir(), in, out, din, dout);
            dispatcher.init();
            System.out.println("enter \"help\" for a list of programme command");
            System.out.println(din.readUTF());
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
