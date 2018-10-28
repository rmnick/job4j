package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleArrayTest {
    @Test
    public void whenAddThenAddingElement() {
        SimpleArray<Integer> arr = new SimpleArray<>(3);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        assertThat(arr.get(0) + arr.get(2), is(8));
    }

    @Test
    public void whenSetThenSettingElement() {
        SimpleArray<String> arr = new SimpleArray<>(2);
        arr.add("3");
        arr.add("4");
        arr.set(1, "v");
        assertThat(String.format("%s %s", arr.get(0), arr.get(1)), is("3 v"));
    }

    @Test(expected = NullPointerException.class)
    public void whenDeleteThenDeletingElement() {
        SimpleArray<String> arr = new SimpleArray<>(3);
        arr.add("ghj");
        arr.add("hk");
        arr.add("hkjj");
        arr.delete(2);
        arr.get(2).length();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenIndexOfThenArrayOfBoundsException() {
        SimpleArray<Integer> arr = new SimpleArray<>(3);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
    }

    @Test
    public void whenIterationThenRightOrderOfElements() {
        SimpleArray<Integer> arr = new SimpleArray<>(3);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        Iterator<Integer> iterator = arr.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(false));
    }
}
