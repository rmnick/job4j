package ru.job4j.start;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {
    /**
     * @param хранит ссылку на объект .
     */
    private Input input;
    /**
     * @param хранит ссылку на объект .
     */
    private Tracker tracker;
    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(this.new AddItem(0, "Add new item"));
        //this.actions.add(this.new ShowItems(1, "Show all items"));
       // this.actions.add(new MenuTracker.EditItem(2, "Edit item"));
       // this.actions.add(new MenuTracker.DeleteItem(3, "Delete item"));
       // this.actions.add(new FindItemById(4, "Find item by Id"));
       // this.actions.add(new FindItemsByName(5, "Find items by name"));
       // this.actions.add(new ExitProgram(6, "Exit Program"));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    public class AddItem implements UserAction {
        private String info;
        private int position;

        AddItem(int position, String info) {
            this.info = info;
            this.position = position;
        }

        @Override
        public int key() {
           return this.position;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding new item --------------");
            String name = input.ask("Please, enter item name:");
            String desc = input.ask("Please, enter item description:");
            Item item = new Item(name, desc);
            tracker.addItem(item);
            System.out.println("------------ New Item with Id : " + item.getId());
            System.out.println("------------ New Item with Name : " + item.getName());
            System.out.println("------------ New Item with Description : " + item.getDescription());
        }

        @Override
        public String info() {
            return this.info;
        }
    }
}
