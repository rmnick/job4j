package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.start.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SQLTest {
    @Test
    public void test() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }
}
