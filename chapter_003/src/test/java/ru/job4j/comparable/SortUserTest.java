package ru.job4j.comparable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SortUserTest {
    @Test
    public void whenListThenSortSet() {
        SortUser sortUser = new SortUser();
        User serg = new User("Sergey", 23);
        User nick = new User("Nick", 34);
        User dima = new User("Dima", 12);
        User ivan = new User("Ivan", 27);
        List<User> users = Arrays.asList(serg, nick, dima, ivan);
        Set<User> set = sortUser.sort(users);
        assertThat(set.toString(), is("[Dima, Sergey, Ivan, Nick]"));
    }
}
