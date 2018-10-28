package ru.job4j.userstore;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {
    public class AddThread extends Thread {
        private UserStore store;
        private final int id;
        public AddThread(int id, UserStore store) {
            this.id = id;
            this.store = store;
        }

        @Override
        public void run() {
            store.add(new UserStore.User(id, 0));
        }
    }


    @Test
    public void addTest() {
        UserStore store = new UserStore();
        assertThat(store.size(), is(0));
        UserStore.User petya = new UserStore.User(1, 500);
        UserStore.User vasya = new UserStore.User(2, 0);
        assertThat(store.add(petya), is(true));
        assertThat(store.add(vasya), is(true));
        assertThat(store.add(petya), is(false));
        assertThat(store.size(), is(2));
        for (int i = 3; i < 10000; i++) {
            new AddThread(i, store).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(store.size(), is(9999));
    }
    @Test
    public void deleteTest() {
        UserStore store = new UserStore();
        UserStore.User petya = new UserStore.User(1, 500);
        store.add(petya);
        assertThat(store.size(), is(1));
        assertThat(store.delete(petya), is(true));
        assertThat(store.size(), is(0));
        assertThat(store.delete(petya), is(false));
    }
    @Test
    public void transferTest() {
        UserStore store = new UserStore();
        UserStore.User petya = new UserStore.User(1, 100000);
        UserStore.User vasya = new UserStore.User(2, 0);
        store.add(petya);
        store.add(vasya);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    store.transfer(petya.getId(), vasya.getId(), 100);
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(petya.getAmount(), is(0));
        assertThat(vasya.getAmount(), is(100000));
    }
}
