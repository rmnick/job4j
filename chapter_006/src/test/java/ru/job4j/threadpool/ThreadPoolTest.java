package ru.job4j.threadpool;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadPoolTest {
    @Test
    public void test() {

        final List<Integer> list = new CopyOnWriteArrayList<>();

        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.work(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j < 11; j++) {
                        list.add(j);
                    }
                }
            });
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sum = list.stream().reduce((s1, s2) -> s1 + s2).orElse(0);
        assertThat(sum, is(275));
        pool.shutdown();
        List<String> threadName = pool.getThreadName();
        threadName.sort(String::compareTo);
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            temp.add("Thread-" + i);
        }
        assertThat(temp, is(threadName));
    }
}

