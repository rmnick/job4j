package ru.job4j.unblock;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UnblockedCasheTest {
    /*
    @Test
    public void test() throws InterruptedException {
        UnblockedCashe map = new UnblockedCashe();
        Base model = new Base(1, 0, "0");
        map.add(model);
        AtomicReference<Exception> ex = new AtomicReference<>();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                String name = "1";
                try {
                    map.change(1, name);
                } catch (OptimisticException e) {
                    ex.set(e);
                    System.out.println(model.getName());
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                model.update(String.valueOf(i));
            }
        }).start();
        Thread.sleep(3000);
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
    */
}
