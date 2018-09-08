package ru.job4j.search.linkedlist;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    public void put(Task task) {
        if (tasks.size() == 0) {
            tasks.add(task);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getPriority() >= task.getPriority()) {
                    tasks.add(i, task);
                    break;
                } else {
                    tasks.addLast(task);
                    break;
                }
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}

