package generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] array;
    private int index = 0;

    public SimpleArray(final int length) {
        array = new Object[length];
    }

    public T add(T model) throws ArrayIndexOutOfBoundsException {
        if (this.index == array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[this.index++] = model;
        return model;
    }

    public void set(int index, T model) throws ArrayIndexOutOfBoundsException {
        if (!(index < array.length)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = model;
    }

    public void delete(int index) throws ArrayIndexOutOfBoundsException {
        if (!(index < array.length)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = null;
    }

    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (!(index < array.length)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[index++];
            }
        };
    }
}
