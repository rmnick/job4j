package ru.job4j.generic.store;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StoreTest {
    @Test
    public void allTests() {
        User petya = new User("1");
        User senya = new User("2");
        User vasya = new User("3");
        User kolya = new User("4");
        User mitya = new User("5");
        UserStore store = new UserStore(5);
        store.add(petya);
        store.add(senya);
        store.add(vasya);
        store.add(kolya);
        assertThat(store.findById("3"), is(vasya));
        store.replace("3", mitya);
        assertThat(store.findById("5"), is(mitya));
        assertThat(store.findById("2"), is(senya));
        store.delete("2");
        assertThat(store.findById("2") == null, is(true));
    }
}
