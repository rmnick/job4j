package set;

import list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArrayList<E> list;

    public SimpleSet() {
        this.list = new SimpleArrayList<>();
    }

    public boolean add(E value) throws NullPointerException {
        boolean result = true;
        Iterator<E> iterator = this.iterator();
        if (value == null) {
            throw new NullPointerException();
        }
        while (iterator.hasNext()) {
            if (value.equals(iterator.next())) {
                result = false;
                break;
            }
        }
        if (result) {
            list.add(value);
        }
        return result;
    }

    public Iterator<E> iterator() {
        return list.iterator();
    }
}
