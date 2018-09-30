package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedArrayIterator implements Iterator {
    private final int[][] arr;
    private int indexY = 0;
    private int indexX = 0;

    JaggedArrayIterator(final int[][] arr) {
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return arr.length > 0 ? !(arr.length - 1 == indexY && arr[indexY].length - 1 < indexX) : false;
    }

    @Override
    public Object next() throws NoSuchElementException {
       if (!hasNext()) {
           throw new NoSuchElementException();
       }
       if (indexX == arr[indexY].length) {
           indexX = 0;
           indexY++;
       }
        return arr[indexY][indexX++];
    }
}
