package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList<E> implements Iterable<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private int modCount = 0;

    private static class Node<E> {

        E value;
        Node<E> prev;
        Node<E> next;

        Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public void add(E value) {
        Node<E> temp = last;
        Node<E> newElement = new Node<>(value, last, null);
        last = newElement;
        if (temp == null) {
            first = newElement;
        } else {
            temp.next = newElement;
        }
        size++;
        modCount++;
    }

    public E get(int index) throws IndexOutOfBoundsException {

        Node<E> result = first;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.value;
    }

    public Iterator<E> iterator() {

        return new Iterator<E>() {

            int expectedModCount = modCount;
            Node<E> pointer = new Node<>(null, null, first);

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (pointer.next != null) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                pointer = pointer.next;
                return pointer.value;
            }
        };
    }
}
