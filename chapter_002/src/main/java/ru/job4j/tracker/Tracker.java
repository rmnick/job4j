package ru.job4j.tracker;

import java.util.*;

/**
 * @author Nick Rodionov
 * @since 2018.08.21
 */

public class Tracker {
    private Item[] items = new Item[100];
    private static final Random RN = new Random();
    private int position = 0;

    public Item addItem(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    public void replace(String id, Item item) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
                break;
            }
        }
    }

    public void delete(String id) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
                this.position--;
            }
        }
    }

    public Item[] findAll() {
        Item[] result = new Item[this.position];
        System.arraycopy(this.items, 0, result, 0, this.position);
        return result;
    }

    public Item[] findByName(String key) {
        Item[] temp = new Item[this.items.length];
        int j = 0;
        int flag = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                temp[j] = this.items[i];
                j++;
                flag = 1;
            }
        }
        Item[] result = new Item[j + flag];
        System.arraycopy(temp, 0, result, 0, j + flag);
        return  result;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item i : this.items) {
            if (i.getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public Item[] getAll() {
        return this.items;
    }

    private String generateId() {
        return String.valueOf(RN.nextInt(100) + System.currentTimeMillis());
    }

}
