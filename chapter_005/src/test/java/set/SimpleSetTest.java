package set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleSetTest {
    @Test
    public void addingTests() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("a");
        assertThat(set.add("s"), is(true));
        set.add("d");
        assertThat(set.add("s"), is(false));
        assertThat(set.add("d"), is(false));
    }
    @Test
    public void iterationTests() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("a");
        assertThat(set.add("s"), is(true));
        set.add("d");
        Iterator<String> iterator = set.iterator();
        StringBuilder str = new StringBuilder();
        while (iterator.hasNext()) {
            str.append(iterator.next());
        }
        assertThat(str.toString(), is("asd"));
    }
}
