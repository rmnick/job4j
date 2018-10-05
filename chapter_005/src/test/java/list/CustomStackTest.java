package list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static  org.hamcrest.Matchers.is;

public class CustomStackTest {
    @Test
    public void pushPopTests() {
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.pop(), is(3));
        assertThat(stack.pop(), is(2));
        assertThat(stack.pop(), is(1));
    }
}

