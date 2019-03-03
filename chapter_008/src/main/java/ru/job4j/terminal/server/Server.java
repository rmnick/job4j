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

    /**
     * main method on server side
     * create Dispatcher for dispatch calculation according with client commands
     * start conversation with client
     */
    public void start() {
        File parent = new File(System.getProperty("user.dir"));
        String ask;
        try (InputStream in = this.socket.getInputStream();
             OutputStream out = this.socket.getOutputStream();
             DataInputStream din = new DataInputStream(in);
             DataOutputStream dout = new DataOutputStream(out)) {
            Dispatcher dis = new Dispatcher(parent, in, out, din, dout);
            dis.init();
            dout.writeUTF(parent.getAbsolutePath());
            do {
                System.out.println("wait");
                ask = din.readUTF();
                if (!ask.equals("exit")) {
                    dis.get(ask);
                }
            } while (!ask.equals("exit"));
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
