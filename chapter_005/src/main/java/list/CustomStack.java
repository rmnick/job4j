package list;

import java.util.EmptyStackException;

public class CustomStack<E> extends CustomLinkedList<E> {

    public E push(E value) {
        super.add(value);
        return value;
    }

    public E pop() throws EmptyStackException {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = last.value;
        last.next = null;
        last = last.prev;
        size--;
        return result;
    }
}
