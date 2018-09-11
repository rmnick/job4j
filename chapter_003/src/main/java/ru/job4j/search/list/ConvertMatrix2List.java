package ru.job4j.search.list;

import java.util.ArrayList;
import java.util.List;

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
