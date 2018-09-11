package ru.job4j.start;

/**
 * @author Nick Rodionov
 * @since 2018.08.21
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestTracker {
    @Test
    public void testWhenAddItem() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("first", "first task");
        Item itemSecond = new Item("second", "second task");
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        assertThat(tr.getAll().get(1), is(itemSecond));
    }
    @Test
    public void testWhenReplaceItem() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("one", "first task");
        Item itemSecond = new Item("two", "second task");
        Item itemThird = new Item("three", "third task");
        Item item = new Item("new", "test item");
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        tr.addItem(itemThird);
        item.setId(tr.getAll().get(1).getId());
        tr.replace(itemSecond.getId(), item);
        assertThat(tr.findById(item.getId()).getName(), is("new"));
    }
    @Test
    public void testWhenDeleteItem() {
        Tracker tr = new Tracker();
        Item[] items = new Item[] {new Item("one", "first task"),
                new Item("two", "second task"),
                new Item("three", "third task")
        };
        tr.addItem(items[0]);
        tr.addItem(items[1]);
        tr.addItem(items[2]);
        tr.delete(items[1].getId());
        List<Item> example = Arrays.asList(items[0], items[2]);
        assertThat(tr.findAll(), is(example));
    }
    @Test
    public void testWhenFindAllItem() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("one", "first task");
        Item itemSecond = new Item("two", "second task");
        Item itemThird = new Item("three", "third task");
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        tr.addItem(itemThird);
        assertThat(tr.findAll().size(), is(3));
    }
    @Test
    public void testWhenFindByNameItem() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("one", "first task");
        Item itemSecond = new Item("two", "second task");
        Item itemThird = new Item("three", "third task");
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        tr.addItem(itemThird);
        assertThat(tr.findByName("three").get(0).getDescription(), is("third task"));
    }
    @Test
    public void testWhenFindById() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("one", "first task");
        Item itemSecond = new Item("two", "second task");
        Item itemThird = new Item("three", "third task");
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        tr.addItem(itemThird);
        assertThat(tr.findById(itemSecond.getId()), is(tr.getAll().get(1)));
    }

}
