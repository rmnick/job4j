package ru.job4j.sort;

import java.io.*;

public class SorterByBytes {
    public static void main(String[] args) {
        int sizeOfSource = 100;

        String path = "/home/nick/work/job4j/chapter_008/src/main/resources/";
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

        split(300, path,"source",0);
    }

    public static int split(long size, String path, String name, int n) {
        int k = 0;
        try (RandomAccessFile raf = new RandomAccessFile(String.format("%s%s.txt", path, name), "rw")) {
            long flag = raf.length() / 2;
            String temp;
            int m = n;
            BufferedWriter bwOne = new BufferedWriter(new FileWriter(new File(String.format("%s%d.txt", path, m))));
            while (raf.getFilePointer() < flag) {
                temp = raf.readLine();
                bwOne.write(temp + System.lineSeparator());
            }
            bwOne.flush();

            k = m + 1;
            BufferedWriter bwTwo = new BufferedWriter(new FileWriter(new File(String.format("%s%d.txt", path, k))));
            while ((temp = raf.readLine()) != null) {
                bwTwo.write(temp + System.lineSeparator());
            }
            bwTwo.flush();
            bwOne.close();
            bwTwo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return k;
    }
}
