package list.checkcycle;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ScannerTest {
    @Test
    public void whenItHasCycleLastAndFirstThenTrue() {
        Scanner<Integer> scanner = new Scanner<>();
        Node<Integer> first = new Node<>(1, null);
        Node<Integer> second = new Node<>(2, null);
        Node<Integer> third = new Node<>(3, null);
        Node<Integer> forth = new Node<>(4, null);
        first.next = second;
        second.next = third;
        third.next = forth;
        forth.next = first;
        assertThat(scanner.hasCycle(first), is(true));
    }
    @Test
    public void whenItHasCycleInTheMiddleThenTrue() {
        Scanner<Integer> scanner = new Scanner<>();
        Node<Integer> first = new Node<>(1, null);
        Node<Integer> second = new Node<>(2, null);
        Node<Integer> third = new Node<>(3, null);
        Node<Integer> forth = new Node<>(4, null);
        first.next = second;
        second.next = first;
        third.next = forth;
        assertThat(scanner.hasCycle(first), is(true));
    }
    @Test
    public void whenItHasCycleWithOneElementThenTrue() {
        Scanner<Integer> scanner = new Scanner<>();
        Node<Integer> first = new Node<>(1, null);
        first.next = first;
        assertThat(scanner.hasCycle(first), is(true));
    }
    @Test
    public void whenItHasNotCycleThenFalse() {
        Scanner<Integer> scanner = new Scanner<>();
        Node<Integer> first = new Node<>(1, null);
        Node<Integer> second = new Node<>(2, null);
        Node<Integer> third = new Node<>(3, null);
        Node<Integer> forth = new Node<>(4, null);
        first.next = second;
        second.next = third;
        third.next = forth;
        assertThat(scanner.hasCycle(first), is(false));
    }
}
