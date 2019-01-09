package ru.job4j.sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.stream.Collectors;

public class Sorter {

    public static void main(String[] args) {
        String namePath = "/home/nick/work/job4j/chapter_008/";
        try (RandomAccessFile out = new RandomAccessFile(String.format("%sout.txt", namePath), "rw");
             RandomAccessFile source = new RandomAccessFile(String.format("%ssource.txt", namePath), "rw")) {
            int sizeOfsource = 53;
            long pointer = 0;
            int sizeOfSegment = 5;
            List<RandomAccessFile> list = new ArrayList<>();
            List<String> storage = new ArrayList();
            /**
             * fill source file
             */
            for (int i = 0; i < sizeOfsource; i++) {
                StringBuilder str = new StringBuilder();
                int n = (int) (Math.random() * 30);
                for (int j = 0; j <= n; j++) {
                    str.append("a");
                }
                source.write((str.toString() + System.lineSeparator()).getBytes());
            }
            /**
             * split source file on small sorted segments
             */
            source.seek(0);
            int countSegments = 0;
            RandomAccessFile temp = null;
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
                //list.add(temp);
               // temp.close();
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
                String shablon = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                for (int i = 0; i < countSegments; i++) {
                    temp = list.get(i);
                    pointerOfSegments[i] = temp.getFilePointer();
                    String s = temp.readLine();
                    if (s != null && s.length() < shablon.length()) {
                        shablon = s;
                        flag = i;
                    }
                }
                out.write((shablon + System.lineSeparator()).getBytes());
                /**
                 * put the pointer on right place
                 */
                for (int j = 0; j < countSegments; j++) {
                    if (j != flag) {
                        list.get(j).seek(pointerOfSegments[j]);
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
