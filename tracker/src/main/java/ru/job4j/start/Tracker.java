package ru.job4j.start;

import java.util.Random;

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

    public boolean replace(String id, Item item) {
        boolean flag = false;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                flag = true;
                item.setId(id);
                this.items[i] = item;
                break;
            }
        }
        return flag;
    }

    public boolean delete(String id) {
        boolean flag = false;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                flag = true;
                System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
                this.position--;
                break;
            }
        }
        return flag;
    }

    public Item[] findAll() {
        Item[] result = new Item[this.position];
        System.arraycopy(this.items, 0, result, 0, this.position);
        return result;
    }

    public Item[] findByName(String key) {
        Item[] temp = new Item[this.items.length];
        int j = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                temp[j] = this.items[i];
                j++;
            }
        }
        Item[] result = new Item[j];
        System.arraycopy(temp, 0, result, 0, j);
        return  result;
    }

    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = this.items[i];
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