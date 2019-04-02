package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.*;
import ru.job4j.foodstorage.storage.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FoodBusiness {
    public final ControlQuality cq;

    private FoodBusiness(final ControlQuality cq) {
        this.cq = cq;
    }

    /**
     * add storage in ControlQuality
     */
    public void fill() {
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
        return food;
    }


    public void run() {
        List<Food> food = delivery();
        cq.sort(food);
    }

    public static void main(String[] args) {
        ControlQuality cq = new ControlQuality();
        FoodBusiness fb = new FoodBusiness(cq);
        fb.fill();
        fb.run();
        /**
         * show sorting result
         */
        cq.getStorage().forEach(IStorage::show);

    }

}
