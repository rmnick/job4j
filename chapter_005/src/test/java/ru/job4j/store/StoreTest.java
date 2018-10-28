package ru.job4j.store;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StoreTest {
    @Test
    public void testing() {
        Store store = new Store();
        List<Store.User> prev = new ArrayList<>();
        List<Store.User> cur = new ArrayList<>();
        Store.User a = new Store.User(1, "Vasya");
        Store.User b = new Store.User(2, "Petya");
        Store.User c = new Store.User(3, "Semen");
        Store.User d = new Store.User(4, "Nick");
        Store.User e = new Store.User(5, "Michail");
        Store.User f = new Store.User(6, "Sergey");
        prev.add(a);
        prev.add(b);
        prev.add(c);
        prev.add(d);
        prev.add(e);
        prev.add(f);
        Store.User g = new Store.User(6, "Kirill");
        Store.User h = new Store.User(7, "Igor");
        cur.add(a);
        cur.add(d);
        cur.add(e);
        cur.add(g);
        cur.add(h);
        Info info = store.diff(prev, cur);
        assertThat(info.getAddedUsers().toString(), is("[7 Igor]"));
        assertThat(info.getChangedUsers().toString(), is("[6 Sergey]"));
        assertThat(info.getDeletedUsers().toString(), is("[2 Petya, 3 Semen]"));
    }
}
