package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void sortArr() {
        BubbleSort arr = new BubbleSort();
        int[] exist = new int[] {7, 8, 15, 24, 1, 13, 2, 2, 33, 4, 2, 45, 4};
        int[] expect = new int[] {1, 2, 2, 2, 4, 4, 7, 8, 13, 15, 24, 33, 45};
        assertThat(arr.sort(exist), is(expect));
    }
}
