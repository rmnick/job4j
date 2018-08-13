package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    @Test
    public void testMatrixWhenSizeIs4() {
        Matrix exmp = new Matrix();
        int[][] expect = {{1, 2, 3, 4}, {2, 4, 6, 8}, {3, 6, 9, 12}, {4, 8, 12, 16}};
        int[][] result = exmp.multiply(4);
        assertThat(result, is(expect));
    }
    @Test
    public void testMatrixWhenSizeIs2() {
        Matrix exmp = new Matrix();
        int[][] expect = {{1, 2}, {2, 4}};
        int[][] result = exmp.multiply(2);
        assertThat(result, is(expect));
    }
}
