package ru.job4j.map;

import java.util.*;

public class CustomHashMap<K, V> implements Iterable<Map.Entry<K, V>> {
    private Entry[] table;
    private final float loadFactor = 0.6f;
    private static final int MAX_CAPACITY = 1 << 30;
    private int size = 0;
    private int modCount = 0;

    public CustomHashMap() {
        this.table = new Entry[16];
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        int hash;

        Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            this.value = value;
            return value;
        }

        int getHash() {
            return hash;
        }
    }

    /**
     * hash function from jdk 7
     * @param h
     * @return
     */
    private int hash(int h) {
        h = h ^ (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * index calculation from jdk 7
     * @param h
     * @param length
     * @return
     */

    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private boolean put(K key, V value) {
        int h = hash(key.hashCode());
        int index = indexFor(h, table.length);
        if (table[index] != null) {
            return false;
        } else {
            table[index] = new Entry(key, value, h);
            size++;
            modCount++;
            return true;
        }
    }

    private Entry[] resize() {
        Entry[] temp = new Entry[this.table.length * 2];
        temp[0] = table[0];
        for (int i = 1; i < this.table.length; i++) {
            if (table[i] != null) {
                if (temp[indexFor(table[i].getHash(), temp.length)] != null) {
                    for (int j = 1; j < temp.length; j++) {
                        if (temp[j] == null) {
                            temp[j] = table[i];
                            break;
                        }
                    }
                } else {
                    temp[indexFor(table[i].getHash(), temp.length)] = table[i];
                }
            }
        }
        return temp;
    }

    boolean insert(K key, V value) {
        if (size == MAX_CAPACITY) {
            return false;
        }
        if (key == null) {
            if (table[0] == null) {
                table[0] = new Entry(key, value, 0);
                size++;
                modCount++;
                return true;
            }
            return false;
        }
        if (table.length * loadFactor > size) {
            return put(key, value);
        } else {
            table = this.resize();
            return put(key, value);
        }
    }

    public V get(K key) {
        V result = null;
        Entry<K, V> e = null;
        if (key == null) {
            if (table[0] != null) {
                e = table[0];
                result = e.getValue();
            }
        } else {
            int h = hash(key.hashCode());
            int index = indexFor(h, table.length);
            if (table[index] != null) {
                e = table[index];
                if (e.getKey().equals(key)) {
                result = e.getValue();
                }
            }
        }
        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        Entry<K, V> e = null;
        if (key == null) {
            if (table[0] != null) {
                table[0] = null;
                size--;
                modCount++;
                result = true;
            }
        } else {
            int h = hash(key.hashCode());
            int index = indexFor(h, table.length);
            if (table[index] != null) {
                e = table[index];
                if (e.getKey().equals(key)) {
                    table[index] = null;
                    size--;
                    modCount++;
                    result = true;
                }
            }
        }
        return result;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<Map.Entry<K, V>>() {
            int count = 0;
            int index = 0;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }
            @Override
            public Entry<K, V> next() throws NoSuchElementException {
                Entry<K, V> e = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        count++;
                        e = table[i];
                        index = i + 1;
                        break;
                    }
                }
                return e;
            }
        };
    }

    /**
     *
     * there's methods for tests(below):
     * getTableLength - return length incapsulate array(base container)
     * getValues and getKeys for print result in test
     */

    public int getTableLength() {
        return table.length;
    }

    public List<V> getValues() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> e : table) {
            if (e != null) {
                values.add(e.getValue());
            }
        }
        return values;
    }

    public Set<K> getKeys() {
        Set<K> keys = new HashSet<>();
        for (Entry<K, V> e : table) {
            if (e != null) {
                keys.add(e.getKey());
            }
        }
        return keys;
    }

}
