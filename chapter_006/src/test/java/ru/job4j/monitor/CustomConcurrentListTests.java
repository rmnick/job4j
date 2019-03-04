package ru.job4j.monitor;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CustomConcurrentListTests {
    public class AddThread<E> extends Thread {
        private CustomConcurrentList<E> list;
        private final E element;

        public AddThread(CustomConcurrentList<E> list, E element) {
            this.list = list;
            this.element = element;
        }

        @Override
        public void run() {
            list.add(element);
        }
    }

    @Test
    public void whenAddingInIterationThereIsNotExceptionAndChangingSnapshot() {
        CustomConcurrentList<Integer> list = new CustomConcurrentList<>(new SimpleArrayList<>());
        //create list with 1000 elements
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        int countSnapshot = 0;
        int dummy = 10;
        Iterator<Integer> snapshotIterator = list.iterator();
        while (snapshotIterator.hasNext()) {
           countSnapshot++;
           snapshotIterator.next();
           new AddThread(list, dummy).start();
        }
        int countNewList = 0;
        Iterator<Integer> newIterator = list.iterator();
        while (newIterator.hasNext()) {
            countNewList++;
            newIterator.next();
        }
        //there is not changing in snapshot even if we're making "add" operation
        assertThat(countSnapshot == 1000, is(true));
        //some changing list because we've made several "add" operation
        assertThat(countNewList != countSnapshot, is(true));
    }

}
