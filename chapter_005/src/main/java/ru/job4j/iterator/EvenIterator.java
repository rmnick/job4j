package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private final int[] arr;
    private int index = 0;

    EvenIterator(final int[] arr) {
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int counter = index; counter < arr.length; counter++) {
            if (arr[counter] % 2 == 0) {
                result = true;
                index = counter;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return arr[index++];
    }
}
