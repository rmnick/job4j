package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    public Iterator<Integer> convert(final Iterator<Iterator<Integer>> iter) {
        return new Iterator<Integer>() {
            private Iterator index;
            private boolean checkCurrentIterator() {
                return index != null && index.hasNext();
            }

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (!checkCurrentIterator()) {
                    for (; iter.hasNext();) {
                        index = iter.next();
                        if (checkCurrentIterator()) {
                            result = true;
                            break;
                        }
                    }
                } else {
                    result = true;
                }
                return result;
            }

            @Override
            public Integer next() throws NoSuchElementException {
                if (!this.hasNext()) {
                   throw new NoSuchElementException();
                }
                return (Integer) index.next();
            }
        };
    }
}
