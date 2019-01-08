package ru.job4j.sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Sorter {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        RandomAccessFile out = new RandomAccessFile("C:\\projects\\job4j\\chapter_008\\out.txt", "rw");
        RandomAccessFile raf = new RandomAccessFile(new File("C:\\projects\\job4j\\chapter_008\\raf.txt"), "rw");
        long pointer = 0;
        List<RandomAccessFile> list = new ArrayList<>();
 /*       Set<String> storages = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() == o2.length() ? 0 : (o1.length() > o2.length() ? 1 : -1);
            }
        });*/
        String[] storage = new String[10];
        for (int i = 0; i < 50; i++) {
            int n = (int) (Math.random() * 30);
            for (int j = 0; j <= n; j++) {
                raf.writeChars(String.valueOf('a'));
            }
            raf.writeChars(String.valueOf("\n"));
        }
        // raf.close();
        raf.seek(0);
        int countFile = 0;
        RandomAccessFile temp = null;
        while (pointer < raf.length()) {
            //raf.seek(pointer);
            int count = 0;
            //storage.clear();
            raf.seek(pointer);
            while (count < 10 && pointer < raf.length()) {
                String tempI = (raf.readLine());
                //System.out.println(tempI);
                storage[count] = (tempI);
                pointer = raf.getFilePointer();
                count++;
            }
            Arrays.sort(storage, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() == o2.length() ? 0 : (o1.length() > o2.length() ? 1 : -1);
                }
            });


            // System.out.println("!!!!!!!!!!!!");
            temp = new RandomAccessFile(new File(String.format("C:/projects/job4j/chapter_008/%d.txt", countFile)), "rw");
           // Iterator<String> iter = storage.iterator();
            temp.seek(0);
            for (int i = 0; i < storage.length; i++) {
                //System.out.println(iter.next());
                temp.writeChars(storage[i] + "\n");
            }
            countFile++;
            //list.add(temp);
        }
        long[] point = new long[countFile];
        for (int i = 0; i < countFile; i++) {
            list.add(new RandomAccessFile(String.format("C:/projects/job4j/chapter_008/%d.txt", i), "rw"));
            //out.writeChars(list.get(i).readLine() + "\n");
        }
        //raf.close();
        String shablon = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        int counter = 0;
        int flag = 0;
        while (counter < 50) {
            for (int i = 0; i < countFile; i++) {
                temp = list.get(i);
                point[i] = temp.getFilePointer();
                String s = temp.readLine();
                if (s.length() < shablon.length()) {
                    shablon = s;
                    flag = i;
                }
                //list.add(new RandomAccessFile(String.format("C:/projects/job4j/chapter_008/%d.txt", i), "rw"));
                //out.writeChars(list.get(i).readLine() + "\n");
                counter++;
            }
            for (int j = 0; j < countFile; j++) {
                if (j != flag) {
                    list.get(j).seek(point[j]);
                }
            }

        }

        raf.close();
        out.close();
    }
        //raf.close();
       // temp.close();
}
