package ru.job4j.start;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * @author Nick Rodionov
 * @since 2018.08.21
 */

public class Tracker {
   // private Item[] items = new Item[100];
    private List<Item> items = new ArrayList<>();
    private static final Random RN = new Random();
    private int position = 0;

    public Item addItem(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    public boolean replace(String id, Item item) {
        boolean flag = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                flag = true;
                item.setId(id);
                this.items.set(i, item);
                break;
            }
        }
        return flag;
    }

    public boolean delete(String id) {
        boolean flag = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                flag = true;
                items.remove(i);
                break;
            }
        }
        return flag;
    }

    public List<Item> findAll() {
        return this.items;
    }

    public List<Item> findByName(Predicate<String> predicate) {
        List<Item> temp = new ArrayList<>();
        for (Item i : this.items) {
            if (predicate.test(i.getName())) {
                temp.add(i);
            }
        }
        return  temp;
    }

    public Item findById(Predicate<String> predicate) {
        Item result = null;
        for (Item i : this.items) {
           if (predicate.test(i.getId())) {
               result = i;
               break;
           }
       }
        return result;
    }

    public List<Item> getAll() {
        return this.items;
    }

    private String generateId() {
        return String.valueOf(RN.nextInt(100) + System.currentTimeMillis());
    }

}
