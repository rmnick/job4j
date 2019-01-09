package ru.job4j.sort;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.stream.Collectors;

public class Sorter {

    public void createSource(String namePath, int sizeOfSource) {
        try (RandomAccessFile source = new RandomAccessFile(String.format("%ssource.txt", namePath), "rw")) {
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

    public void sort(String namePath, int sizeOfSegment) {
        try (RandomAccessFile out = new RandomAccessFile(String.format("%sout.txt", namePath), "rw");
             RandomAccessFile source = new RandomAccessFile(String.format("%ssource.txt", namePath), "rw")) {
            long pointer = 0;
            List<RandomAccessFile> list = new ArrayList<>();
            List<String> storage = new ArrayList();
            /**
             * split source file on small sorted segments
             */
            int countSegments = 0;
            RandomAccessFile temp;
            while (pointer < source.length()) {
                int count = 0;
                while (count < sizeOfSegment && pointer < source.length()) {
                    String tempI = (source.readLine());
                    storage.add(count, tempI);
                    pointer = source.getFilePointer();
                    count++;
                }

                storage = storage.stream().sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.length() == o2.length() ? 0 : (o1.length() > o2.length() ? 1 : -1);
                    }
                }).collect(Collectors.toList());

                temp = new RandomAccessFile(new File(String.format("%s%d.txt", namePath, countSegments)), "rw");
                temp.seek(0);
                for (int i = 0; i < storage.size(); i++) {
                    temp.write((storage.get(i) + System.lineSeparator()).getBytes());
                }
                countSegments++;
                storage.clear();
            }
            /**
             * make list of segments and array of their pointers
             */
            long[] pointerOfSegments = new long[countSegments];
            for (int i = 0; i < countSegments; i++) {
                list.add(new RandomAccessFile(String.format("%s%d.txt", namePath, i), "rw"));
            }
            /**
             * merge all segments in one file
             */
            int flag = 0;
            while (out.length() < source.length()) {
                String temporary = null;
                for (int i = 0; i < countSegments; i++) {
                    temp = list.get(i);
                    pointerOfSegments[i] = temp.getFilePointer();
                    String s = temp.readLine();
                    if (temporary == null) {
                        temporary = s;
                    }
                    if (s != null && s.length() <= temporary.length()) {
                        temporary = s;
                        flag = i;
                    }
                }
                out.write((temporary + System.lineSeparator()).getBytes());
                /**
                 * put the pointer on right place
                 */
                for (int j = 0; j < countSegments; j++) {
                    if (j != flag) {
                        list.get(j).seek(pointerOfSegments[j]);
                    }
                }
            }
            for (RandomAccessFile raf : list) {
                raf.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
