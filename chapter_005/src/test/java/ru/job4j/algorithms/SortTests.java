package ru.job4j.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SortTests {
    @Test
    public void bubbleSortTesting() {
        int[] arrEven = new int[] {3, 1, 0, 90, 11, 12, 5, 2};
        Sort.bubbleSort(arrEven);
        List<Integer> list = Arrays.stream(arrEven).boxed().collect(Collectors.toList());
        StringJoiner str = new StringJoiner(" ", "", "");
        list.stream().map(num -> String.valueOf(num)).forEach(str :: add);
        assertThat(str.toString(), is("0 1 2 3 5 11 12 90"));
        int[] arrOdd = new int[] {3, 1, 6, 0, 90, 6, 11, 12, 5, 2, 3};
        Sort.bubbleSort(arrOdd);
        list = Arrays.stream(arrOdd).boxed().collect(Collectors.toList());
        str = new StringJoiner(" ", "", "");
        list.stream().map(num -> String.valueOf(num)).forEach(str :: add);
        assertThat(str.toString(), is("0 1 2 3 3 5 6 6 11 12 90"));
    }

    @Test
    public void selectedSortTesting() {
        int[] arrEven = new int[] {3, 1, 0, 90, 11, 12, 5, 2};
        Sort.selectedSort(arrEven);
        List<Integer> list = Arrays.stream(arrEven).boxed().collect(Collectors.toList());
        StringJoiner str = new StringJoiner(" ", "", "");
        list.stream().map(num -> String.valueOf(num)).forEach(str :: add);
        assertThat(str.toString(), is("0 1 2 3 5 11 12 90"));
        int[] arrOdd = new int[] {3, 1, 6, 0, 90, 6, 11, 12, 5, 2, 0};
        Sort.selectedSort(arrOdd);
        list = Arrays.stream(arrOdd).boxed().collect(Collectors.toList());
        str = new StringJoiner(" ", "", "");
        list.stream().map(num -> String.valueOf(num)).forEach(str :: add);
        assertThat(str.toString(), is("0 0 1 2 3 5 6 6 11 12 90"));
    }

    @Test
    public void insertedSortTesting() {
        int[] arrEven = new int[] {3, 1, 0, 90, 11, 12, 5, 2};
        Sort.insertedSort(arrEven);
        List<Integer> list = Arrays.stream(arrEven).boxed().collect(Collectors.toList());
        StringJoiner str = new StringJoiner(" ", "", "");
        list.stream().map(num -> String.valueOf(num)).forEach(str :: add);
        assertThat(str.toString(), is("0 1 2 3 5 11 12 90"));
        int[] arrOdd = new int[] {3, 1, 6, 0, 90, 6, 11, 12, 5, 2, 0};
        Sort.insertedSort(arrOdd);
        list = Arrays.stream(arrOdd).boxed().collect(Collectors.toList());
        str = new StringJoiner(" ", "", "");
        list.stream().map(num -> String.valueOf(num)).forEach(str :: add);
        assertThat(str.toString(), is("0 0 1 2 3 5 6 6 11 12 90"));
    }
}
