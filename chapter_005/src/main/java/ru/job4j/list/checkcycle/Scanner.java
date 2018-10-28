package ru.job4j.list.checkcycle;

public class Scanner<E> {
    public boolean hasCycle(Node<E> first) {
        Node<E> fastIter = first;
        Node<E> slowIter = first;
        boolean result = false;
        while (fastIter != null && fastIter.next != null) {
            slowIter = slowIter.next;
            fastIter = fastIter.next.next;
            if (slowIter == fastIter) {
                result = true;
                break;
            }
        }
        return result;
    }
}
