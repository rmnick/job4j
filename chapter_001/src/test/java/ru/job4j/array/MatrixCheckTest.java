package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixCheckTest {
    @Test
    public void whenEvenLengthResultIsTrue() {
        MatrixCheck matr = new MatrixCheck();
        boolean[][] arr = {
                {true, false, true, false},
                {true, true, false, true},
                {true, false, true, true},
                {false, false, false, true}
        };
        assertThat(matr.checkMatrix(arr), is(true));
    }

    @Test
    public void whenOddLengthResultIsTrue() {
        MatrixCheck matr = new MatrixCheck();
        boolean[][] arr = {
                {false, false, true, false, false },
                {true, false, false, false, false},
                {true, true, false, true, false},
                {false, false, false, false, false},
                {false, false, true, false, false}
        };
        assertThat(matr.checkMatrix(arr), is(true));
    }

    @Test
    public void whenEvenLengthResultIsFalse() {
        MatrixCheck matr = new MatrixCheck();
        boolean[][] arr = {
                {true, false, true, false},
                {true, true, false, true},
                {true, false, true, true},
                {true, false, false, true}
        };
        assertThat(matr.checkMatrix(arr), is(false));
    }

    @Test
    public void whenOddLengthResultIsFalse() {
        MatrixCheck matr = new MatrixCheck();
        boolean[][] arr = {
                {false, false, true, false, true },
                {true, false, false, false, false},
                {true, true, false, true, false},
                {false, false, false, false, false},
                {false, false, true, false, false}
        };
        assertThat(matr.checkMatrix(arr), is(false));
    }
}
