package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MergeTest {
    @Test
    public void testMerge() {
        Merge exmpl = new Merge();
        int[] arrA = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arrB = {3, 5, 8, 9, 19, 20 };
        int[] result = {1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 8, 9, 9, 10, 19, 20};
        assertThat(exmpl.mergeAB(arrA, arrB), is(result));
    }
}
