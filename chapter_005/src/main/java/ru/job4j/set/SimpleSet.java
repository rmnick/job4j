package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArrayList<E> list;

    public SimpleSet() {
        this.list = new SimpleArrayList<>();
    }

    public boolean add(E value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException();
        }
        boolean result = !this.contains(value);
        if (result) {
            list.add(value);
        }
        return result;
    }

    public Iterator<E> iterator() {
        return list.iterator();
    }

    private boolean contains(E value) {
        boolean result = false;
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (value.equals(iterator.next())) {
                result = true;
                break;
            }
        }
        return result;
    }
}
