package monitor;

import list.SimpleArrayList;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
@ThreadSafe
public class CustomConcurrentList<E> implements Iterable<E> {
@GuardedBy("this")
    private SimpleArrayList<E> list;

    public CustomConcurrentList(SimpleArrayList<E> list) {
        this.list = list;
    }

    public synchronized void add(E element) {
        list.add(element);
    }

    public synchronized E get(int index) {
        return list.get(index);
    }

    public synchronized Iterator<E> iterator() {
        return copy(list).iterator();
    }

    private SimpleArrayList<E> copy(SimpleArrayList<E> list) {
        SimpleArrayList<E> snapshot = new SimpleArrayList<>();
        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()) {
            snapshot.add(iterator.next());
        }
        return snapshot;
    }
}
