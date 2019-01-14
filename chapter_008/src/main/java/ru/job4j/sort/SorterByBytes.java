package ru.job4j.sort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SorterByBytes {

    /**
     * create folder if that doesnt exist
     * create source file and fill it
     * @param path
     * @param sizeOfSource
     */
    public void create(String path, String name, int sizeOfSource) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try (RandomAccessFile source = new RandomAccessFile(String.format("%s%s%s.txt", path, File.separator, name), "rw")) {
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
     * main method
     * @param path
     * @param size
     * @return
     */
    public void run(String path, long size) {
        splitAll(path, size);
        sort(path);
        mergeAll(path);
    }

    /**
     * split file into smaller pieces according to the specified size
     * @param file
     */
    private void split(File file, int n) {
        File source = new File(file.getPath());
        try (RandomAccessFile raf = new RandomAccessFile(file.getPath(), "rw")) {
            long flag = raf.length() / 2;
            String temp;

            File fileOne = new File(String.format("%s/%dright.txt", file.getParent(), n));
            BufferedWriter bwOne = new BufferedWriter(new FileWriter(fileOne));
            while (raf.getFilePointer() < flag) {
                temp = raf.readLine();
                bwOne.write(temp + System.lineSeparator());
            }
            bwOne.flush();

            File fileTwo = new File(String.format("%s/%dleft.txt", file.getParent(), n));
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
        source.delete();
    }

    /**
     * split all files one by one
     * @param path
     * @param size
     */
    private void splitAll(String path, long size) {
        boolean flag = true;
        int n = 0;
        List<File> list;
        while (flag) {
            list = collect(path);
            for (File file : list) {
                if (file.exists() && file.length() > size) {
                    split(file, n++);
                    flag = true;
                } else {
                    flag = false;
                }
            }
            list.clear();
        }
    }

    /**
     * sort every file contents one by one in folder
     * delete old, create new
     * @param path
     */
    private void sort(String path) {
        try {
            List<File> files = collect(path);
            List<String> list;
            int n = 0;
            for (File file : files) {
                list = Files.lines(Paths.get(file.getPath())).sorted().collect(Collectors.toList());
                file.delete();
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
    private void mergeAll(String path) {
        List<File> list = collect(path);
        int n = 0;
        while (list.size() > 1) {
            for (int i = 0, j = 1; j < list.size(); i = i + 2, j = j + 2) {
                merge(list.get(i), list.get(j), n++);
            }
            list.clear();
            list = collect(path);
        }
    }

    /**
     * merge two file by order and return one
     * @param one
     * @param two
     * @param count
     * @return
     */
    private void merge(File one, File two, int count) {
        try (RandomAccessFile rafOne = new RandomAccessFile(one, "rw");
             RandomAccessFile rafTwo = new RandomAccessFile(two, "rw");
             RandomAccessFile out = new RandomAccessFile(new File(String.format("%s/%dout.txt", one.getParent(), count)), "rw")) {
            long size = rafOne.length() + rafTwo.length();
            long pointerOne;
            long pointerTwo;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        one.delete();
        two.delete();
    }

    /**
     * check and collect all existing files from that folder
     * @param path
     * @return
     */
    public List<File> collect(String path) {
        List<File> list = new ArrayList<>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.exists()) {
                list.add(file);
            }
        }
        return list;
    }
}
