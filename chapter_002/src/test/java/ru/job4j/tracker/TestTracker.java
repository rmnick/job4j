package ru.job4j.tracker;

/**
 * @author Nick Rodionov
 * @since 2018.08.21
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestTracker {
    @Test
    public void testWhenAddItem() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("first", "first task", 24L);
        Item itemSecond = new Item("second", "second task", 245L);
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        assertThat(tr.getAll()[1], is(itemSecond));
    }
    @Test
    public void testWhenReplaceItem() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("one", "first task", 24L);
        Item itemSecond = new Item("two", "second task", 245L);
        Item itemThird = new Item("three", "third task", 2555L);
        Item item = new Item("new", "test item", 366L);
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        tr.addItem(itemThird);
        item.setId(tr.getAll()[1].getId());
        tr.replace(itemSecond.getId(), item);
        assertThat(tr.findById(item.getId()).getName(), is("new"));
    }
    @Test
    public void testWhenDeleteItem() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("one", "first task", 24L);
        Item itemSecond = new Item("two", "second task", 245L);
        Item itemThird = new Item("three", "third task", 2555L);
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        tr.addItem(itemThird);
        tr.delete(itemSecond.getId());
        assertThat(tr.getAll()[1], is(itemThird));
    }
    @Test
    public void testWhenFindAllItem() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("one", "first task", 24L);
        Item itemSecond = new Item("two", "second task", 245L);
        Item itemThird = new Item("three", "third task", 2555L);
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        tr.addItem(itemThird);
        assertThat(tr.findAll().length, is(3));
    }
    @Test
    public void testWhenFindByNameItem() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("one", "first task", 24L);
        Item itemSecond = new Item("two", "second task", 245L);
        Item itemThird = new Item("three", "third task", 2555L);
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        tr.addItem(itemThird);
        assertThat(tr.findByName("three")[0].getDescription(), is("third task"));
    }
    @Test
    public void testWhenFindById() {
        Tracker tr = new Tracker();
        Item itemFirst = new Item("one", "first task", 24L);
        Item itemSecond = new Item("two", "second task", 245L);
        Item itemThird = new Item("three", "third task", 2555L);
        tr.addItem(itemFirst);
        tr.addItem(itemSecond);
        tr.addItem(itemThird);
        assertThat(tr.findById(itemSecond.getId()), is(tr.getAll()[1]));
    }

}
