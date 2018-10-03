package generic.store;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StoreTest {
    @Test
    public void allTests() {
        User Petya = new User("1");
        User Sergey = new User("2");
        User Vasya = new User("3");
        User Kolya = new User("4");
        User Mitya = new User("5");
        UserStore<User> store = new UserStore<>(5);
        store.add(Petya);
        store.add(Sergey);
        store.add(Vasya);
        store.add(Kolya);
        //assertThat(store.findById("3"), is(Vasya));
        store.replace("3", Mitya);
        assertThat(store.findById("3"), is(Mitya));
    }
}
