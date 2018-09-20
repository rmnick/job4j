package ru.job4j.start;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.After;
import org.junit.Before;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.List;

public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final String menu = new StringJoiner(System.lineSeparator(), System.lineSeparator(), System.lineSeparator())
            .add("Menu: ")
            .add("0. Add new Item")
            .add("1. Show all items")
            .add("2. Edit item")
            .add("3. Delete item")
            .add("4. Find item by Id")
            .add("5. Find items by name")
            .add("6. Exit Program")
            .toString();

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(Arrays.asList("0", "first", "first task", "6"));
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(tracker.findAll().get(0).getName(), is("first"));
    }

    @Test
        public void whenUpdateThenTrackerHasUpdatedValue() {
            Tracker tracker = new Tracker();
            Item item = tracker.addItem(new Item("previous", "description"));
            Input input = new StubInput(Arrays.asList("2", item.getId(), "replace task", "new description", "6"));
            StartUI start = new StartUI(input, tracker);
            start.init();
            assertThat(tracker.findById((id) -> id.equals(item.getId())).getName(), is("replace task"));

    }

    @Test
    public void whenDeleteThenTrackerHasDeletedValueByValidId() {
        Tracker tracker = new Tracker();
        Item[] items = new Item[] {new Item("one", "first task"),
                new Item("two", "second task"),
                new Item("three", "third task")
        };
        tracker.addItem(items[0]);
        tracker.addItem(items[1]);
        tracker.addItem(items[2]);
        List<Item> exm = Arrays.asList(items[0], items[2]);
        Input input = new StubInput(Arrays.asList("3", items[1].getId(), "6"));
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(tracker.findAll(), is(exm));
    }

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenShowAllThenShow() {
        Tracker tracker = new Tracker();
        Item itemFirst = tracker.addItem(new Item("first", "description first"));
        Item itemSecond = tracker.addItem(new Item("second", "description second"));
        Input input = new StubInput(Arrays.asList("1", "6"));
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add(menu)
                        .add("------------ All tasks --------------")
                        .add(itemFirst.getName() + " " + itemFirst.getDescription() + " " + itemFirst.getId())
                        .add(itemSecond.getName() + " " + itemSecond.getDescription() + " " + itemSecond.getId())
                        .add(menu)
                        .add("------------ End of session --------------")
                        .toString()
                )
        );
    }
    @Test
    public void whenFindByIdThenItemWithThisId() {
        Tracker tracker = new Tracker();
        Item itemFirst = tracker.addItem(new Item("first", "description first"));
        Item itemSecond = tracker.addItem(new Item("second", "description second"));
        Item itemThird = tracker.addItem(new Item("third", "description third"));

        Input input = new StubInput(Arrays.asList("4", itemSecond.getId(), "6"));
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add(menu)
                        .add("------------ Searching a task by Id --------------")
                        .add(itemSecond.getName() + " " + itemSecond.getDescription() + " " + itemSecond.getId())
                        .add(menu)
                        .add("------------ End of session --------------")
                        .toString()
                )
        );
    }
    @Test
    public void whenFindByNameThenItemsWithThisName() {
        Tracker tracker = new Tracker();
        Item itemFirst = tracker.addItem(new Item("first", "description first"));
        Item itemSecond = tracker.addItem(new Item("second", "description second"));
        Item itemThird = tracker.addItem(new Item("third", "description third"));

        Input input = new StubInput(Arrays.asList("5", itemThird.getName(), "6"));
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add(menu)
                        .add("------------ Searching a task by name --------------")
                        .add(itemThird.getName() + " " + itemThird.getDescription() + " " + itemThird.getId())
                        .add(menu)
                        .add("------------ End of session --------------")
                        .toString()
                )
        );
    }
    @Test
    public void whenDeleteByInvalidIdThenTrackerWontDelete() {
        Tracker tracker = new Tracker();
        Item itemFirst = tracker.addItem(new Item("first", "description first"));
        Item itemSecond = tracker.addItem(new Item("second", "description second"));
        Input input = new StubInput(Arrays.asList("3", "12", "6"));
        StartUI start = new StartUI(input, tracker);
        start.init();
        assertThat(new String(out.toByteArray()), is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add(menu)
                        .add("------------ Removing a task --------------")
                        .add("------------ Id doesn't exist -----------")
                        .add(menu)
                        .add("------------ End of session --------------")
                        .toString()
                )
        );
    }
    @Test
    public void whenInvalidInput() {
        List<Integer> range = new ArrayList<Integer>();
        ValidateInput input = new ValidateInput(
                new StubInput(Arrays.asList("invalid", "1"))
        );
        input.ask("Enter", range);
        assertThat(
                this.out.toString(),
                is(
                        String.format("Please enter validate data again.%n")
                )
        );
    }

}
