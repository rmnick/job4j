package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
       List<Integer> list = new ArrayList<>();
        for (int[] x : array) {
            for (int y : x) {
                list.add(y);
            }
        }
        return list;
    }

    public List<Integer> toList(List<int[]> list) {
        List<Integer> arrayList = new ArrayList<>();
        for (int[]x : list) {
            for (int y : x) {
                arrayList.add(y);
            }
        }
        return arrayList;

    }
}
