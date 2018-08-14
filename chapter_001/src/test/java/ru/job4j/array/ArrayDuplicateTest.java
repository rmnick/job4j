package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ArrayDuplicateTest {
    @Test
    public void duplicateRemove() {
        ArrayDuplicate temp = new ArrayDuplicate();
        String[] input = {"1", "1", "3", "5", "2", "3", "4", "1"};
        String[] expect = {"1", "4", "3", "5", "2"};
        assertThat(temp.remove(input), is(expect));
    }
}
