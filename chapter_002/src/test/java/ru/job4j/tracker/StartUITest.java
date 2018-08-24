package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"0", "first", "first task", "6"});
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(tracker.findAll()[0].getName(), is("first"));
    }
    @Test
        public void whenUpdateThenTrackerHasUpdatedValue() {
            Tracker tracker = new Tracker();
            Item item = tracker.addItem(new Item("previous", "description"));
            Input input = new StubInput(new String[] {"2", item.getId(), "replace task", "new description", "6"});
            StartUI start = new StartUI(input, tracker);
            start.init();
            assertThat(tracker.findById(item.getId()).getName(), is("replace task"));

    }
    @Test
    public void whenDeleteThenTrackerHasDeletedValue() {
        Tracker tracker = new Tracker();
        Item itemFirst = tracker.addItem(new Item("first", "description first"));
        Item itemSecond = tracker.addItem(new Item("second", "description second"));
        Item itemThird = tracker.addItem(new Item("third", "description third"));

        Input input = new StubInput(new String[]{"3", itemSecond.getId(), "6"});
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(tracker.findAll()[1].getName(), is("third"));
    }
}
