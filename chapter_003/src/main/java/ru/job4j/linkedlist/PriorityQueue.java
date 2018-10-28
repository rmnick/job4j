package ru.job4j.linkedlist;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    public void put(Task task) {
        int index = tasks.size();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getPriority() > task.getPriority()) {
                index = i;
                break;
            }
        }
        tasks.add(index, task);
    }
    /*
    public void put(Task task) {
        int index = tasks.stream()
                .filter(item -> item.getPriority() > task.getPriority())
                .ru.job4j.map(item -> tasks.indexOf(item)).findFirst().get();
        tasks.add(index, task);
    }
    */
    public Task take() {
        return this.tasks.poll();
    }
}

