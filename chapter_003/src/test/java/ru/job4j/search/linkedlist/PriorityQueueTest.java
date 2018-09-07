package ru.job4j.search.linkedlist;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low-low", 5));
        queue.put(new Task("medium", 4));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("low", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}
