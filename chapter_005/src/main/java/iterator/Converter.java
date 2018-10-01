package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    public Iterator<Integer> convert(final Iterator<Iterator<Integer>> iter) {
        return new Iterator<Integer>() {
            Iterator index = iter.next();
            @Override
            public boolean hasNext() {
                return iter.hasNext() || index.hasNext();
            }

            @Override
            public Integer next() throws NoSuchElementException{
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (!index.hasNext()) {
                    index = iter.next();
                }
                return (Integer) index.next();
            }
        };
    }
}
