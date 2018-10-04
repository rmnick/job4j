package list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static  org.hamcrest.Matchers.is;

public class SimpleArrayListTest {
    @Test
    public void addGetTests() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertThat(list.get(2), is(3));
        list.add(5);
        assertThat(list.get(0), is(1));
        assertThat(list.get(4), is(5));
        assertThat(list.get(5) == null, is(true));
    }
    @Test
    public void iterationTests() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(false));
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenIterationAndModificationThenException() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(4);
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        list.add(4);
        iterator.next();
    }
    @Test
    public void whenLengthIsZeroThenIncreaseAndAdd() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(0);
        list.add(1);
        assertThat(list.get(0), is(1));
    }
}
