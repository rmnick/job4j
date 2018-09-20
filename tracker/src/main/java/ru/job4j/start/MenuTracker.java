package ru.job4j.start;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {
    /**
     * stores a reference to an object.
     */
    private final Input input;
    /**
     * stores a reference to an object.
     */
    private final Tracker tracker;
    /**
     * stores a reference to an object of type UserAction.
     */
    private final List<UserAction> actions = new ArrayList<>();
    /**
     * stores a reference to key of UserAction.
     */
    private final List<Integer> keys = new ArrayList<>();
    /**
     * Constructor.
     * @param input   an object of type Input.
     * @param tracker an object of type Tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method to return the length of an action array.
     * @return length array.
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Method creates array of actions.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(this.new AddItem(0, "Add new Item"));
        this.actions.add(this.new ShowAll(1, "Show all items"));
        this.actions.add(this.new EditItem(2, "Edit item"));
        this.actions.add(this.new DeleteItem(3, "Delete item"));
        this.actions.add(new MenuTracker.FindById(4, "Find item by Id"));
        this.actions.add(new MenuTracker.FindByName(5, "Find items by name"));
        this.actions.add(new ExitProgram(6, "Exit Program", ui));
    }

    /**
     * Method creates array of keys.
     */
    public void fillKeys() {
        for (int i = 0; i < this.getActionsLentgh(); i++) {
            this.keys.add(this.actions.get(i).key());
        }
    }

    /**
     * Method does something because of key.
     *
     * @param key key of action.
     */
    public int select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
        return key;
    }

    /**
     * Method prints menu.
     */
    public void show() {
        StringBuilder menu = new StringBuilder();
        menu.append(System.lineSeparator()).append("Menu: ").append(System.lineSeparator());
        for (UserAction action : this.actions) {
            if (action != null) {
                menu.append(action.info()).append(System.lineSeparator());
            }
        }
        System.out.println(menu.toString());
    }

    /**
     * Method prints menu.
     */
    public List<Integer> getKeys() {
        return this.keys;
    }

    /**
     * add
     */
    public class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding a new task --------------");
            String name = input.ask("Enter a name for the new task: ");
            String description = input.ask("Enter a description: ");
            Item item = new Item(name, description);
            tracker.addItem(item);
            System.out.println("------------ Id of this task: " + item.getId() + "-----------");
        }
    }

    /**
     * show
     */
    public class ShowAll extends BaseAction {

        public ShowAll(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ All tasks --------------");
            for (Item item : tracker.findAll()) {
                System.out.println(item.toString());
            }
        }
    }

    /**
     * edit
     */
    public class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Editing a task --------------");
            String id = input.ask("Enter Id: ");
            String name = input.ask("Enter new name for task: ");
            String description = input.ask("Enter new description: ");
            Item item = new Item(name, description);
            if (tracker.replace(id, item)) {
                System.out.println("------------ The task has been changed -----------");
            } else {
                System.out.println("------------ Id doesn't exist -----------");
            }
        }
    }

    /**
     * delete
     */
    public class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Removing a task --------------");
            String id = input.ask("Enter Id: ");
            if (tracker.delete(id)) {
                System.out.println("------------ The task with " + id + " Id" + " has been deleted -----------");
            } else {
                System.out.println("------------ Id doesn't exist -----------");
            }
        }
    }

    /**
     * findById
     */
    public static class FindById extends BaseAction {

        public FindById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Searching a task by Id --------------");
            String id = input.ask("Enter Id: ");
            Item item = tracker.findById(result -> result.equals(id));
            if (item != null) {
                System.out.println(item.toString());
            } else {
                System.out.println("------------ Id doesn't exist --------------");
            }
        }
    }

    /**
     * findByName
     */
    public static class FindByName extends BaseAction {

        public FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Searching a task by name --------------");
            String name = input.ask("Enter name: ");
            List<Item> items = tracker.findByName(result -> result.equals(name));
            items.forEach(System.out :: println);
        }
    }
}

/**
 * exit
 */
class ExitProgram extends BaseAction {
    private final StartUI ui;

    public ExitProgram(int key, String name, StartUI ui) {
        super(key, name);
        this.ui = ui;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ End of session --------------");
        this.ui.stop();
    }
}
