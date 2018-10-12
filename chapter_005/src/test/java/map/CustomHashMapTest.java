package map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static  org.hamcrest.Matchers.is;

public class CustomHashMapTest {
    @Test
    public void whenInsertEqualKeysThenFalse() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        assertThat(map.insert(3, "c"), is(true));
        assertThat(map.insert(2, "b"), is(false));
    }
    @Test
    public void whenInsertDifferentKeysWithSameHashThenFalse() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        assertThat(map.insert(5, "e"), is(true));
        assertThat(map.insert(6, "f"), is(true));
        assertThat(map.insert(66, "dummy"), is(false));
    }
    @Test
    public void whenInsertManyKeysThenSizeIncrease() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
        map.insert(4, "d");
        map.insert(5, "e");
        map.insert(6, "f");
        assertThat(map.getTableLength(), is(16));
        map.insert(7, "g");
        map.insert(8, "h");
        map.insert(9, "k");
        map.insert(10, "j");
        map.insert(11, "i");
        map.insert(12, "m");
        assertThat(map.getTableLength(), is(32));
        assertThat(map.getValues().toString(), is("[a, b, c, d, e, f, g, h, k, j, i, m]"));
    }
    @Test
    public void whenGetRightKeyThenValue() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
        assertThat(map.get(2), is("b"));
    }
    @Test
    public void whenGetWrongKeyThenNull() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
        assertThat(map.get(4) == null, is(true));
    }
    @Test
    public void whenGetWrongKeyWithExistHashThenNull() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(6, "f");
        assertThat(map.get(66) == null, is(true));
    }
    @Test
    public void whenDeleteRightKeyThenRemoveValueAndTrue() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
        map.insert(4, "d");
        assertThat(map.delete(3), is(true));
        assertThat(map.getValues().toString(), is("[a, b, d]"));
    }
    @Test
    public void whenDeleteWrongKeyThenFalse() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
        assertThat(map.delete(4), is(false));
    }
    @Test
    public void whenIteratorHasNextThenNext() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
        Iterator<Map.Entry<Integer, String>> iterator = map.iterator();
        assertThat(iterator.next().getValue(), is("a"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("b"));
        assertThat(iterator.next().getValue(), is("c"));
        assertThat(iterator.hasNext(), is(false));
    }
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorHasNotNextThenException() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        Iterator<Map.Entry<Integer, String>> iterator = map.iterator();
        iterator.next();
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorNextDuringChangingMapThenException() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
        Iterator<Map.Entry<Integer, String>> iterator = map.iterator();
        iterator.next();
        map.insert(4, "d");
        iterator.next();
    }
}
