package list;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class CustomStack<E> {
    private final CustomLinkedList<E> list;

    public CustomStack() {
        this.list = new CustomLinkedList<>();
    }

    public E push(E value) {
        this.list.add(value);
        return value;
    }

    public E poll() throws EmptyStackException {
        try {
            return this.list.removeLast();
        } catch (NoSuchElementException e) {
            throw new EmptyStackException();
        }
    }

    public int getSize() {
        return list.getSize();
    }
}
