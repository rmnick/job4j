package ru.job4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class WiseOracleServer {
    private final Socket socket;
    private final Map<String, String> answers;

    public WiseOracleServer(final Socket socket) {
        this.socket = socket;
        this.answers = this.create();
    }

    private Map<String, String> create() {
        Map<String,  String> answers = new HashMap<>();
        answers.put("hello", "Hello, dear friend, I'm a oracle.");
        answers.put("time", "i haven't got a watch");
        answers.put("name", "My name is Oracle");
        answers.put("bye", "Bye, my friend");
        return answers;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String ask;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                out.println(answer(ask));
                out.println();
            } while (!"bye".equals(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String answer(String str) {
        String answer = "I don't understand what you mean";
        for (String key : answers.keySet()) {
            if (str.toLowerCase().contains(key)) {
                answer = answers.get(key);
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Config config = new Config();
        try (Socket socket = new ServerSocket(Integer.parseInt(config.get("port"))).accept()) {
            new WiseOracleServer(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
