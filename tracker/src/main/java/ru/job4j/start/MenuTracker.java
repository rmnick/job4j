package ru.job4j.start;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MenuTracker {
    /**
     * stores a reference to an object.
     */
    private Input input;
    /**
     * stores a reference to an object.
     */
    private Tracker tracker;
    /**
     * stores a reference to an object of type UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

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
    public void fillActions() {
        this.actions.add(this.new AddItem());
        this.actions.add(this.new ShowAll());
        this.actions.add(this.new EditItem());
        this.actions.add(this.new DeleteItem());
        this.actions.add(new MenuTracker.FindById());
        this.actions.add(new MenuTracker.FindByName());
        this.actions.add(new ExitProgram());
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
        for (UserAction action : this.actions) {
            if (action != null) {
                menu.append(action.info()).append(System.lineSeparator());
            }
        }
        System.out.println(menu.toString());
    }

    /**
     * add
     */
    public class AddItem implements UserAction {

        @Override
        public int key() {
           return 0;
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

        @Override
        public String info() {
            return "0. Add new Item";
        }
    }

    /**
     * show
     */
    public class ShowAll implements UserAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ All tasks --------------");
            for (Item item : tracker.findAll()) {
                System.out.println(item.toString());
            }
        }

        @Override
        public String info() {
            return "1. Show all items";
        }
    }

    /**
     * edit
     */
    public class EditItem implements UserAction {

        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Editing a task --------------");
            String id = input.ask("Enter Id: ");
            String name = input.ask("Enter new name for task: ");
            String description = input.ask("Enter new description: ");
            Item item = new Item(name, description);
            tracker.replace(id, item);
            System.out.println("------------The task has been changed-----------");
        }

        @Override
        public String info() {
            return "2. Edit item";
        }
    }

    /**
     * delete
     */
    public class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Removing a task --------------");
            String id = input.ask("Enter Id: ");
            if (tracker.delete(id)) {
                System.out.println("------------The task with " + id + " Id" + " has been deleted-----------");
            } else {
                System.out.println("------------There's no this id-----------");
            }
        }

        @Override
        public String info() {
            return "3. Delete item";
        }
    }

    /**
     * findById
     */
    public static class FindById implements UserAction {

        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Searching a task by Id --------------");
            String id = input.ask("Enter Id: ");
            Item item = tracker.findById(id);
            System.out.println(item.toString());
        }

        @Override
        public String info() {
            return "4. Find item by Id";
        }
    }

    /**
     * findByName
     */
    public static class FindByName implements UserAction {

        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Searching a task by name --------------");
            String name = input.ask("Enter name: ");
            Item[] items = tracker.findByName(name);
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }

        @Override
        public String info() {
            return "5. Find items by name";
        }
    }
}

/**
 * exit
 */
class ExitProgram implements UserAction {

    @Override
    public int key() {
        return 6;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ End of session --------------");
    }

    @Override
    public String info() {
        return "6. Exit Program";
    }
}
