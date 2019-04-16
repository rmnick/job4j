package ru.job4j.foodstorage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.foodstorage.food.*;
import ru.job4j.foodstorage.storage.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ControlQualityTest {
    private final PrintStream defaultOut = System.out;
    private final ByteArrayOutputStream customOut = new ByteArrayOutputStream();

    @Before
    public void setCustomOutput() {
        System.setOut(new PrintStream(this.customOut));
    }

    @After
    public void rollbackCustomOut() {
        System.setOut(this.defaultOut);
    }

    @Test
    public void whenStarResortImmediatelyAfterSortThenEqualResult() {
        /**
         * create and fill food list
         */
        List<Food> food = new ArrayList<>();
        food.add(new Tomatoes(10,
                LocalDateTime.of(2018, 10, 1, 0, 0, 0),
                LocalDateTime.of(2019, 3, 20, 0, 0, 0),
                0, "cherry", false));
        food.add(new Milk(10,
                LocalDateTime.of(2019, 3, 1, 0, 0, 0),
                LocalDateTime.of(2019, 3, 27, 0, 0, 0),
                0, "3,2 milk", false));
        food.add(new Milk(10,
                LocalDateTime.of(2019, 2, 10, 0, 0, 0),
                LocalDateTime.of(2019, 4, 10, 0, 0, 0),
                0, "2,5 milk", false));
        food.add(new Beer(10,
                LocalDateTime.of(2019, 3, 1, 0, 0, 0),
                LocalDateTime.of(2050, 3, 10, 0, 0, 0),
                0, "Bud", false));
        food.add(new Cheese(10,
                LocalDateTime.of(2018, 3, 1, 0, 0, 0),
                LocalDateTime.of(2019, 2, 10, 0, 0, 0),
                0, "cheddar", false));
        food.add(new Tomatoes(10,
                LocalDateTime.of(2019, 3, 1, 0, 0, 0),
                LocalDateTime.of(2019, 5, 20, 0, 0, 0),
                0, "red", false));
        food.add(new Bread(10,
                LocalDateTime.of(2019, 1, 1, 0, 0, 0),
                LocalDateTime.of(2019, 1, 20, 0, 0, 0),
                0, "red", true));
        food.add(new Beer(10,
                LocalDateTime.of(2019, 3, 1, 0, 0, 0),
                LocalDateTime.of(2050, 3, 10, 0, 0, 0),
                0, "Miller", false));
        /**
         * create controlQuality object
         */
        ControlQuality cq = new ControlQuality();
        /**
         * create all stores and fill store list in controlQuality
         */
        AbstractStorage shop = new Shop("shop");
        AbstractStorage trash = new Trash("trash");
        AbstractStorage wareHouse = new WareHouse("wareHouse");
        cq.add(new FrigeStore("fridge", shop));
        cq.add(new WareHouseFixed("fixed", wareHouse));
        cq.add(new WareHouseExtended("extended", wareHouse));
        cq.add(new TrashReproductor("reproductor", trash));
        cq.add(shop);
        cq.add(trash);
        cq.add(wareHouse);

        cq.sort(food);
        cq.getStorage().forEach(IStorage::show);
        String sort = new String(customOut.toByteArray());
        customOut.reset();
        cq.resort();
        cq.getStorage().forEach(IStorage::show);
        String resort = new String(customOut.toByteArray());

        assertThat(sort, is(resort));
    }
}
