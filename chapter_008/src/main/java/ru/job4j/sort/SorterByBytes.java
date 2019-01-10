package ru.job4j.sort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SorterByBytes {
    public static void main(String[] args) throws IOException {
        int sizeOfSource = 100;

        String path = "chapter_008/src/main/resources/";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try (RandomAccessFile source = new RandomAccessFile(String.format("%ssource.txt", path), "rw")) {
            for (int i = 0; i < sizeOfSource; i++) {
                StringBuilder str = new StringBuilder();
                int n = (int) (Math.random() * 30);
                for (int j = 0; j <= n; j++) {
                    str.append("a");
                }
                source.write((str.toString() + System.lineSeparator()).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        split(50, "chapter_008/src/main/resources/source.txt");
        sort(path);

    }

    public static void split(long size, String path) {
        File fileOne = null;
        File fileTwo = null;
        long flag = 0;
        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            flag = raf.length() / 2;
            String temp;
            fileOne = new File(String.format("chapter_008/src/main/resources/%dright.txt", flag));
            BufferedWriter bwOne = new BufferedWriter(new FileWriter(fileOne));
            while (raf.getFilePointer() < flag) {
                temp = raf.readLine();
                bwOne.write(temp + System.lineSeparator());
            }
            bwOne.flush();

            fileTwo = new File(String.format("chapter_008/src/main/resources/%dleft.txt", flag));
            BufferedWriter bwTwo = new BufferedWriter(new FileWriter(fileTwo));
            while ((temp = raf.readLine()) != null) {
                bwTwo.write(temp + System.lineSeparator());
            }
            bwTwo.flush();

            bwOne.close();
            bwTwo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        file.delete();

        if (fileOne != null && fileOne.length() > size) {
            split(flag, fileOne.getPath());
        }

        if (fileTwo != null && fileTwo.length() > size) {
            split(flag, fileTwo.getPath());
        }
    }

    public static void sort(String path) throws IOException {
        List<String> list = new ArrayList<>();
        File index = new File(path);
        String[]entries = index.list();
        int n = 0;
        for (String s : entries) {
            File currentFile = new File(index.getPath(), s);
            list = Files.lines(Paths.get(currentFile.getPath())).sorted().collect(Collectors.toList());
            currentFile.delete();
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path, String.format("%d.txt", n))));
            n++;
            for (String str : list) {
                System.out.println(str);
                bw.write(str + System.lineSeparator());
            }
            bw.flush();
            bw.close();
        }
    }

    public static void mergeAll(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (int i = 0, j = 1; j < files.length - 2; i = i + 2, j = j + 2) {
            System.out.println("a");
        }

    }

    public static void merge(File one, File two, int count) {
        try (RandomAccessFile rafOne = new RandomAccessFile(one, "rw");
             RandomAccessFile rafTwo = new RandomAccessFile(two, "rw");
             RandomAccessFile out = new RandomAccessFile(new File(String.format("%s%d%s", one.getPath(), count, one.getName())), "rw")) {
            System.out.println("a");
        } catch (IOException e) {

        }
    }
}
