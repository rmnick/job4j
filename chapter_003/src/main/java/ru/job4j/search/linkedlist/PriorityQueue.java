package ru.job4j.search.linkedlist;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    public void put(Task task) {
        boolean flag = false;
        if (tasks.size() == 0) {
            tasks.add(task);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (task.getPriority() <= tasks.get(i).getPriority()) {
                    tasks.add(i, task);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                tasks.addLast(task);
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}

