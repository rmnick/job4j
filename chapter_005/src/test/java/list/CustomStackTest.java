package list;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.hamcrest.MatcherAssert.assertThat;
import static  org.hamcrest.Matchers.is;

public class CustomStackTest {
    @Test
    public void pushPopTests() {
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
        stack.push(1);
        stack.push(2);
        assertThat(stack.poll(), is(2));
        stack.push(3);
        assertThat(stack.poll(), is(3));
    }
    @Test(expected = EmptyStackException.class)
    public void whenStackIsEmptyAndPollThenException() {
        CustomStack<Integer> stack = new CustomStack<>();
        stack.poll();
    }
}

