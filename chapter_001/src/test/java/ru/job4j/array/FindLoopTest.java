package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {
    @Test
    public void whenIndexIs4() {
        FindLoop exmp = new FindLoop();
        int[] arr = new int[] {2, 3, 6, 22, 53, 1, 4, 33, 44};
        assertThat(exmp.indexOf(arr, 53), is(4));
    }
    @Test
    public void whenElementDoesntEixst() {
        FindLoop exmp = new FindLoop();
        int[] arr = new int[] {2, 3, 6, 22, 53, 1, 4, 33, 44};
        assertThat(exmp.indexOf(arr, 63), is(-1));
    }

}
