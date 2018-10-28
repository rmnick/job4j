package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleTreeTest {
    @Test
    public void whenAddDifferentValueThenTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 3), is(true));
        assertThat(tree.add(2, 4), is(true));
    }
    @Test
    public void whenAddEqualValueThenFalse() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 3), is(true));
        assertThat(tree.add(2, 3), is(false));
    }
    @Test
    public void whenIteratorHasNextThenNext() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(1, 6);
        tree.add(2, 7);
        tree.add(3, 8);
        tree.add(4, 9);
        tree.add(6, 10);
        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.next(), is(8));
        assertThat(iterator.next(), is(10));
        assertThat(iterator.next(), is(9));
        assertThat(iterator.hasNext(), is(false));
    }
    @Test
    public void whenTreeIsBinaryThenTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 3), is(true));
        assertThat(tree.add(2, 4), is(true));
        assertThat(tree.add(3, 5), is(true));
        assertThat(tree.add(3, 6), is(true));
        assertThat(tree.isBinary(), is(true));
    }
    @Test
    public void whenTreeIsNotBinaryThenFalse() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 3), is(true));
        assertThat(tree.add(2, 4), is(true));
        assertThat(tree.add(3, 5), is(true));
        assertThat(tree.add(3, 6), is(true));
        assertThat(tree.add(3, 7), is(true));
        assertThat(tree.isBinary(), is(false));
    }
}
