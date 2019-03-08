package ru.job4j.menu;

import ru.job4j.menu.items.FirstItem;
import ru.job4j.menu.items.SecondItem;
import ru.job4j.menu.items.TopItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu extends AbstractItem {

    public Menu(String name, List<IDemonstrator> items) {
        super(name, items);
    }

    public void run() {
        this.show();
        Scanner sc = new Scanner(System.in);
        System.out.println("input task for execution");
        String answer = sc.nextLine();
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        /**
         * fill the menu
         */
        SecondItem oneS = new SecondItem("1.1.1 task", new ArrayList<>());
        SecondItem twoS = new SecondItem("1.1.2 task", new ArrayList<>());
        List<IDemonstrator> secondItems = new ArrayList<>();
        secondItems.add(oneS);
        secondItems.add(twoS);
        FirstItem oneF = new FirstItem("1.1 task", secondItems);
        FirstItem twoF = new FirstItem("1.2 task", new ArrayList<>());
        List<IDemonstrator> firstItems = new ArrayList<>();
        firstItems.add(oneF);
        firstItems.add(twoF);
        TopItem oneT = new TopItem("1 task", firstItems);
        List<IDemonstrator> topItems = new ArrayList<>();
        topItems.add(oneT);
        Menu menu = new Menu("---Menu---", topItems);
        /**
         * ....
         */
        menu.run();
    }
}
