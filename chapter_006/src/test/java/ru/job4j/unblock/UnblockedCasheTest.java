package ru.job4j.unblock;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UnblockedCasheTest {
    @Test
    public void test() {
        UnblockedCashe map = new UnblockedCashe();
        Base model = new Base(1, 0, "0");
        map.add(model);
        AtomicReference<Exception> ex = new AtomicReference<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(
                    () -> {
                String name = "1";
                try {
                    map.change(1, name);
                } catch (OptimisticException e) {
                    ex.set(e);
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(ex.get().getMessage(), is("model is already changed"));
    }

    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                });
        thread.start();
        thread.join();
        Assert.assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }
}
