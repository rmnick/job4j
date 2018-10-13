package tree;

import java.util.*;

public class SimpleTree<E extends Comparable<E>> implements Iterable<E> {

    private final Node<E> root;
    private int modCount = 0;

    public SimpleTree(E root) {
        this.root = new Node(root);
    }

    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> tempParent = findBy(parent);
        if (tempParent.isPresent()) {
            Optional<Node<E>> tempChild = findBy(child);
            if (!tempChild.isPresent()) {
                tempParent.get().add(new Node<>(child));
                result = true;
                modCount++;
            }
        }
        return result;
    }

    private static class Node<E extends Comparable<E>> {
        private final List<Node<E>> children = new ArrayList<>();
        private final E value;

        public Node(final E value) {
            this.value = value;
        }

        public void add(Node<E> child) {
            this.children.add(child);
        }

        public List<Node<E>> leaves() {
            return this.children;
        }

        public boolean eqValue(E that) {
            return this.value.compareTo(that) == 0;
        }

        public E getValue() {
            return this.value;
        }
    }

    private Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int count = modCount;
            Node<E> temp = root;
            Queue<Node<E>> queue = new LinkedList<>();
            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                return temp != null;
            }

            @Override
            public E next() throws  NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result;
                for (Node<E> e: temp.leaves()) {
                    queue.offer(e);
                }
                result =  temp.getValue();
                temp = queue.poll();
                return result;
            }
        };
    }
}
