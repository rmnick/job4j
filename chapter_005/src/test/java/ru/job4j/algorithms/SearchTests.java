package ru.job4j.algorithms;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTests {
    @Test
    public void testBinarySearch() {
        int[] arrEven = new int[] {1, 3, 7, 8, 9, 20, 77, 99, 101, 102};
        int[] arrOdd = new int[] {1, 3, 7, 8, 9, 20, 77, 99, 101, 102, 105};
        assertThat(Search.binarySearch(101, arrEven), is(true));
        assertThat(Search.binarySearch(1, arrEven), is(true));
        assertThat(Search.binarySearch(102, arrEven), is(true));
        assertThat(Search.binarySearch(1, arrOdd), is(true));
        assertThat(Search.binarySearch(101, arrOdd), is(true));
        assertThat(Search.binarySearch(105, arrOdd), is(true));
        assertThat(Search.binarySearch(106, arrOdd), is(false));
        assertThat(Search.binarySearch(12, arrEven), is(false));
    }
}
