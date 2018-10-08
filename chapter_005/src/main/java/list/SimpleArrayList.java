package list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<E> implements Iterable<E> {
    private Object[] array;
    private int capacity = 0;
    private long modCount = 0;

    public SimpleArrayList() {
        this.array = new Object[10];
    }

    public SimpleArrayList(int length) {
        this.array = new Object[length];
    }

    public void add(E value) {
        if (!(this.capacity < this.array.length)) {
            array = Arrays.copyOf(this.array, (capacity * 3) / 2 + 1);
        }
        this.array[this.capacity++] = value;
        this.modCount++;
    }

    public E get(int index) {
        return (E) this.array[index];
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            long expectedModCount = modCount;
            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < capacity;
            }

            @Override
            public E next() throws NoSuchElementException, ConcurrentModificationException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) array[index++];
            }
        };
    }
}
