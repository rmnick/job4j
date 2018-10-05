package list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static  org.hamcrest.Matchers.is;

public class CustomLinkedListTest{
    @Test
    public void addGetTests() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertThat(list.get(3), is(4));
        assertThat(list.get(0), is(1));
    }
/*    @Test
    public void iterationTests() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
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
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(false));
    }
    */
}
