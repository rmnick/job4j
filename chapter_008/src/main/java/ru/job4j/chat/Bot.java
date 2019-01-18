package ru.job4j.chat;

import jdk.management.resource.internal.inst.RandomAccessFileRMHooks;

import java.io.*;
import java.util.Scanner;

public class Bot {

    private final String path;
    private final String nameFile;
    private final String logName;
    private final String[] answers;

    public Bot(final String path, final String nameFile, final String logName, final String[] answers) {
        this.path = path;
        this.nameFile = nameFile;
        this.logName = logName;
        this.answers = answers;
    }
    public static void main(String[] args) {
        String[] answers = {"hey", "what is it", "why", "who", "and", "so", "go on", "no", "so what", "how it can be", "lol"};
        String path = "chapter_008/src/main/resources/";
        String name = "file.txt";
        String log = "log.txt";
        Bot john = new Bot(path, name, log, answers);
        john.createListAnswers();
        john.start();
    }

    /**
     * conversation method
     */
    public void start() {
        String answer;
        String botAnswer;
        String hello = "Hey, dude, i'm silly John";
        File logFile = new File(path + logName);
        Scanner sc = new Scanner(System.in);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile))) {
            System.out.println(hello);
            bw.write(hello);
            bw.newLine();
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
                    botAnswer = getAnswer();
                    bw.write(botAnswer);
                    bw.newLine();
                    System.out.println(botAnswer);
                }
            } while (!"exit".equals(answer));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create file with answers
     */
    public void createListAnswers() {
        File file = new File(path + nameFile);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String answer : answers) {
                bw.write(answer);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get random answer from file
     * we jump on the beginning new string from seek(pointer)
     */
    public String getAnswer() {
        String result = null;
        File file = new File(path + nameFile);
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
