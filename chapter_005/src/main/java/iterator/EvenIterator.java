package iterator;

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
        int count = index;
        while (count < arr.length) {
            if (arr[count++] % 2 == 0) {
                result = true;
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
        while (arr[index] % 2 != 0) {
            index++;
        }
        return arr[index++];
    }
}
