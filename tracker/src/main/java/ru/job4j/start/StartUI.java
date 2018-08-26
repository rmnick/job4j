package ru.job4j.start;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nick Rodionov
 * @since 2018.08.23
 */
public class StartUI {
    /**
     * list constants for menu.
     */
    private static final String ADD = "0";
    private static final String SHOW_ALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FIND_BY_ID = "4";
    private static final String FIND_BY_NAME = "5";
    private static final String EXIT = "6";

    private Input input;
    private Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void main(String[] args) {
        StartUI start = new StartUI(new ConsolInput(), new Tracker());
        start.init();
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            menu.select(input.ask("select:"));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }



    /**  public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Enter the number of task: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                    this.showAll();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findItemById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findItemByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    private void createItem() {
        System.out.println("------------ Adding a new task --------------");
        String name = this.input.ask("Enter a name for the new task: ");
        String description = this.input.ask("Enter a description: ");
        Item item = new Item(name, description);
        this.tracker.addItem(item);
        System.out.println("------------ Id of this task: " + item.getId() + "-----------");
    }
*/
    private void showAll() {
        System.out.println("------------ All tasks --------------");
        for (Item item : this.tracker.findAll()) {
            System.out.println(item.toString());
        }
    }

    private void editItem() {
        System.out.println("------------ Editing a task --------------");
        String id = this.input.ask("Enter Id: ");
        String name = this.input.ask("Enter new name for task: ");
        String description = this.input.ask("Enter new description: ");
        Item item = new Item(name, description);
        this.tracker.replace(id, item);
        System.out.println("------------The task has been changed-----------");
    }

    private void deleteItem() {
        System.out.println("------------ Removing a task --------------");
        String id = this.input.ask("Enter Id: ");
        if (this.tracker.delete(id)) {
            System.out.println("------------The task with " + id + " Id" + " has been deleted-----------");
        } else {
            System.out.println("------------There's no this id-----------");
        }
    }

    private void findItemById() {
        System.out.println("------------ Searching a task by Id --------------");
        String id = this.input.ask("Enter Id: ");
        Item item = this.tracker.findById(id);
        System.out.println(item.toString());
    }

    private void findItemByName() {
        System.out.println("------------ Searching a task by name --------------");
        String name = this.input.ask("Enter name: ");
        Item[] items = this.tracker.findByName(name);
        for (Item item : items) {
            System.out.println(item.toString());
        }
    }

    private void showMenu() {
        System.out.println("Menu");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println("Select: ");
    }
}
