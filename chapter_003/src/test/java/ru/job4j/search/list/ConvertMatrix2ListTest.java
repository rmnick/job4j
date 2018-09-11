package ru.job4j.search.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertMatrix2ListTest {
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }
    @Test
    public void whenListWithArraysThenListWithInt() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {6, 7, 8};
        List<int[]> arrayList = new ArrayList<>();
        arrayList.add(one);
        arrayList.add(two);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        assertThat(list.toList(arrayList), is(expect));
    }
}
