package ru.job4j.sort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SorterByBytes {
    public static void main(String[] args) {
        int sizeOfSource = 1000;
        String path = "chapter_008/src/main/resources/";

        create(path, sizeOfSource);
        split(1000, "chapter_008/src/main/resources/source.txt");
        sort(path);
        //mergeAll("chapter_008/src/main/resources/");

    }

    /**
     * create folder if that doesnt exist
     * create source file and fill it
     * @param path
     * @param sizeOfSource
     */
    public static void create(String path, int sizeOfSource) {
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
    }

    /**
     * split file into smaller pieces according to the specified size
     * @param size
     * @param path
     */
    public static void split(long size, String path) {
        File fileOne = null;
        File fileTwo = null;
        long flag;
        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            File file = new File(path);
            //System.out.println(file.length());
            flag = raf.length() / 2;
            String temp;
            fileOne = new File(String.format("%s/%dright.txt", file.getParent(), flag));
            BufferedWriter bwOne = new BufferedWriter(new FileWriter(fileOne));
            while (raf.getFilePointer() < flag) {
                temp = raf.readLine();
                bwOne.write(temp + System.lineSeparator());
            }
            bwOne.flush();

            fileTwo = new File(String.format("%s/%dleft.txt", file.getParent(), flag));
            BufferedWriter bwTwo = new BufferedWriter(new FileWriter(fileTwo));
            while ((temp = raf.readLine()) != null) {
                bwTwo.write(temp + System.lineSeparator());
            }
            bwTwo.flush();

            bwOne.close();
            bwTwo.close();

            Files.delete(Paths.get(path));


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileOne.length() > size) {
            split(size, fileOne.getPath());
        }

        if (fileTwo.length() > size) {
            split(size, fileTwo.getPath());
        }
    }

    /**
     * sort every file contents one by one in folder
     * delete old, create new
     * @param path
     */
    public static void sort(String path) {
        try {
            List<String> list;
            File index = new File(path);
            String[] entries = index.list();
            int n = 0;
            for (String s : entries) {
                File currentFile = new File(index.getPath(), s);
                list = Files.lines(Paths.get(currentFile.getPath())).sorted().collect(Collectors.toList());
                currentFile.delete();
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path, String.format("%d.txt", n))));
                n++;
                for (String str : list) {
                    bw.write(str + System.lineSeparator());
                }
                bw.flush();
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * call method "merge" for all files in that folder
     * @param path
     */
    public static void mergeAll(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        List<File> list = new ArrayList<>();
        List<File> out = new ArrayList<>();
        for (File file : files) {
            if (file.exists()) {
                list.add(file);
            }
        }
        while (out.size() != 1) {
            out.clear();
            //System.out.println(list.size());
            for (int i = 0, j = 1; j < list.size(); i = i + 2, j = j + 2) {
                System.out.println(list.get(i) + " " + list.get(j));
                out.add(merge(list.get(i), list.get(j), i + j));
            }
           // System.out.println(out.size());
            list.clear();
            for (File file : out) {
                if (file.exists()) {
                    list.add(file);
                }
            }
        }
    }

    /**
     * merge two file by order and return one
     * @param one
     * @param two
     * @param count
     * @return
     */
    public static File merge(File one, File two, int count) {
        File result = null;
        try (RandomAccessFile rafOne = new RandomAccessFile(one, "rw");
             RandomAccessFile rafTwo = new RandomAccessFile(two, "rw");
             RandomAccessFile out = new RandomAccessFile(new File(String.format("%s/%d%s", one.getParent(), count, one.getName())), "rw")) {
            long size = rafOne.length() + rafTwo.length();
            long pointerOne = 0;
            long pointerTwo = 0;
            String oneStr;
            String twoStr;
            while (out.length() < size) {
                pointerOne = rafOne.getFilePointer();
                pointerTwo = rafTwo.getFilePointer();
                oneStr = rafOne.readLine();
                twoStr = rafTwo.readLine();
                if (oneStr == null || (oneStr != null && twoStr != null && oneStr.length() > twoStr.length())) {
                    rafOne.seek(pointerOne);
                    out.write((twoStr + System.lineSeparator()).getBytes());
                } else {
                    rafTwo.seek(pointerTwo);
                    out.write((oneStr + System.lineSeparator()).getBytes());
                }
            }
            result = new File(String.format("%s/%d%s", one.getParent(), count, one.getName()));
            Files.delete(Paths.get(one.getPath()));
            Files.delete(Paths.get(two.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
