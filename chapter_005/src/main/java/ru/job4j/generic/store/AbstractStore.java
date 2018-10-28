package ru.job4j.generic.store;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    SimpleArray<T> array;

    AbstractStore(int length) {
        array = new SimpleArray<>(length);
    }

    @Override
    public void add(T model) {
        array.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        Iterator<T> iterator = array.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T user = iterator.next();
            if (user != null && user.getId().equals(id)) {
                array.set(index, model);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T element = null;
        Iterator<T> iterator = array.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T some = iterator.next();
            if (some != null && some.getId().equals(id)) {
                element = array.get(index);
                break;
            }
            index++;
        }
        return element;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Iterator<T> iterator = array.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (element != null && element.getId().equals(id)) {
                array.delete(index);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }
}
