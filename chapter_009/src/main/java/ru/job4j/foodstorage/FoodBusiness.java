package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.*;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
import ru.job4j.foodstorage.storage.WareHouse;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FoodBusiness {
    public final ControlQuality cq;

    private FoodBusiness(final ControlQuality cq) {
        this.cq = cq;
    }

    public void init() {
        cq.add(new Shop());
        cq.add(new Trash());
        cq.add(new WareHouse());
    }

    /**
     * create foodstuff list for sorting
     * @return ArrayList
     */
    public List<Food> delivery() {
        List<Food> food = new ArrayList<>();
        food.add(new Tomatoes(10,
                LocalDateTime.of(2018, 10, 1, 0, 0, 0),
                LocalDateTime.of(2019, 3, 20, 0, 0, 0),
                0, "cherry"));
        food.add(new Milk(10,
                LocalDateTime.of(2019, 3, 1, 0, 0, 0),
                LocalDateTime.of(2019, 3, 10, 0, 0, 0),
                0, "3,2"));
        food.add(new Milk(10,
                LocalDateTime.of(2019, 2, 10, 0, 0, 0),
                LocalDateTime.of(2019, 4, 10, 0, 0, 0),
                0, "2,5"));
        food.add(new Beer(10,
                LocalDateTime.of(2019, 3, 1, 0, 0, 0),
                LocalDateTime.of(2050, 3, 10, 0, 0, 0),
                0, "Bud"));
        food.add(new Cheese(10,
                LocalDateTime.of(2018, 3, 1, 0, 0, 0),
                LocalDateTime.of(2019, 2, 10, 0, 0, 0),
                0, "cheddar"));
        return food;
    }


    public void run() {
        List<Food> food = delivery();
        cq.sort(food);
    }

    public static void main(String[] args) {
        ControlQuality cq = new ControlQuality();
        FoodBusiness fb = new FoodBusiness(cq);
        fb.init();
        fb.run();

    }

}
