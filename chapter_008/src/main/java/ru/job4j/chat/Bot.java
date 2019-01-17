package ru.job4j.chat;

import jdk.management.resource.internal.inst.RandomAccessFileRMHooks;

import java.io.*;
import java.util.Scanner;

public class Bot {
    public static void main(String[] args) {
        String[] answers = {"hey", "what is it", "why", "who", "and", "so", "go on", "no", "so what", "how it can be", "lol"};
        String path = "chapter_008/src/main/resources/";
        String name = "file.txt";
        String log = "log.txt";
        BufferedWriter bw = null;
        Bot john = new Bot();
        String answer;
        String botAnswer;

        john.createListAnswers(path + name, answers);

        File logFile = new File(path + log);
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey, dude, i'm silly John");
        try {
            bw = new BufferedWriter(new FileWriter(logFile));
            do {
                answer = sc.nextLine();
                bw.write(answer);
                bw.newLine();
                if (answer.equals("stop")) {
                    while (!(answer.equals("continue") || answer.equals("exit"))) {
                        answer = sc.nextLine();
                        bw.write(answer);
                        bw.newLine();
                    }
                } else {
                    botAnswer = john.getAnswer(path + name);
                    bw.write(botAnswer);
                    bw.newLine();
                    System.out.println(botAnswer);
                }
            } while (!"exit".equals(answer));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * create file with answers
     * @param path
     * @param answers
     */
    public void createListAnswers(String path, String[] answers) {
        File file = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (String answer : answers) {
                bw.write(answer);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * get random answer from file
     * we jump on the beginning new string from seek(pointer)
     * @param path
     * @return
     */
    public String getAnswer(String path) {
        String result = null;
        File file = new File(path);
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long pointer = (long) (Math.random() * raf.length());
            raf.seek(pointer);
            raf.readLine();
            result = raf.readLine();
            if (result == null) {
                raf.seek(0);
                result = raf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
