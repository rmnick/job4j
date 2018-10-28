package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertMatrix2List {
    /*
    public List<Integer> toList(int[][] array) {
       List<Integer> ru.job4j.list = new ArrayList<>();
        for (int[] x : array) {
            for (int y : x) {
                ru.job4j.list.add(y);
            }
        }
        return ru.job4j.list;
    }
    */
    public List<Integer> toList(int[][] array) {
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }

/*
    public List<Integer> toList(List<int[]> ru.job4j.list) {
        List<Integer> arrayList = new ArrayList<>();
        for (int[]x : ru.job4j.list) {
            for (int y : x) {
                arrayList.add(y);
            }
        }
        return arrayList;

    }
*/
     public List<Integer> toList(List<int[]> list) {
         return list.stream().flatMapToInt(Arrays :: stream).boxed().collect(Collectors.toList());
     }
}
