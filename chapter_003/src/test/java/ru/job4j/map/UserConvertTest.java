package ru.job4j.map;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenArrayListThenHashMap() {
        UserConvert test = new UserConvert();
        User ivan = new User(123, "Ivan", "Mgn");
        User serg = new User(235, "Serg", "Msc");
        User andy = new User(453, "Andy", "Kiev");

        Map<Integer, User> map = test.convertToHashMap(Arrays.asList(ivan, serg, andy));
        User example = map.get(235);

        assertThat(example, is(serg));
    }
}
