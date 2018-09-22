package ru.job4j.comparable;

import org.junit.Test;

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
        assertThat(set.toString(), is("[Dima(12), Sergey(23), Ivan(27), Nick(34)]"));
    }
    @Test
    public void whenListThenSortListByNameLength() {
        SortUser sortUser = new SortUser();
        User serg = new User("Sergey", 23);
        User nick = new User("Nickolay", 34);
        User dima = new User("Dimon", 12);
        User ivan = new User("Ivan", 27);
        User ivanJunior = new User("Ivan", 7);
        List<User> users = Arrays.asList(serg, nick, ivanJunior, dima, ivan);
        List<User> list = sortUser.sortNameLength(users);
        assertThat(list.toString(), is("[Ivan(27), Ivan(7), Dimon(12), Sergey(23), Nickolay(34)]"));
    }
    @Test
    public void whenListThenSortListByAllFields() {
        SortUser sortUser = new SortUser();
        User serg = new User("Sergey", 23);
        User dima = new User("Dimon", 10);
        User nick = new User("Nickolay", 34);
        User dimaP = new User("Dimon", 12);
        User ivan = new User("Ivan", 27);
        User sergP = new User("Sergey", 43);
        List<User> users = Arrays.asList(serg, dima, nick, dimaP, ivan, sergP);
        List<User> list = sortUser.sortByAllFields(users);
        assertThat(list.toString(), is("[Dimon(10), Dimon(12), Ivan(27), Nickolay(34), Sergey(23), Sergey(43)]")
        );
    }
}
