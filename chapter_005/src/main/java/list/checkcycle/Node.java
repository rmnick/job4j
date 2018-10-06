package list.checkcycle;

public class Node<E> {
    E value;
    Node<E> next;
    public Node(E value, Node<E> next) {
        this.value = value;
        this.next = next;
    }
}
