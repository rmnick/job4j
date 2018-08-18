package ru.job4j.tictactoe;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Logic3TTest {
    @Test
    public void logicTest() {
        Figure3T exmpl[][] = {
                {new Figure3T(true), new Figure3T(), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(true), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(), new Figure3T(true)}
        };

        Logic3T desk = new Logic3T(exmpl);
        assertThat(desk.isWinnerX(), is(true));
    }


}
