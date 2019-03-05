package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.*;
import ru.job4j.foodstorage.sorters.*;
import ru.job4j.foodstorage.storage.Storage;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
import ru.job4j.foodstorage.storage.WareHouse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodBusiness {
    public final List<ISorter> sorters = new ArrayList<>();
    public final Map<String, Storage> storage = new HashMap<>();
    public final ControllQuality cq;

    private FoodBusiness(final ControllQuality cq) {
        this.cq = cq;
    }

    public void init() {
        this.storage.put("shop", new Shop());
        this.storage.put("warehouse", new WareHouse());
        this.storage.put("trash", new Trash());

        this.sorters.add(new ShopDiscountSorter(this.storage.get("shop")));
        this.sorters.add(new ShopSorter(this.storage.get("shop")));
        this.sorters.add(new TrashSorter(this.storage.get("trash")));
        this.sorters.add(new WareHouseSorter(this.storage.get("warehouse")));
    }

    public List<Food> delivery() {
        List<Food> food = new ArrayList<>();
        food.add(new Tomatoes(10,
                LocalDateTime.of(2018, 10, 1, 0, 0, 0),
                LocalDateTime.of(2019, 3, 20, 0, 0, 0),
                0));
        food.add(new Milk(10,
                LocalDateTime.of(2019, 3, 1, 0, 0, 0),
                LocalDateTime.of(2019, 3, 10, 0, 0, 0),
                0));
        food.add(new Milk(10,
                LocalDateTime.of(2019, 2, 10, 0, 0, 0),
                LocalDateTime.of(2019, 4, 10, 0, 0, 0),
                0));
        food.add(new Beer(10,
                LocalDateTime.of(2019, 3, 1, 0, 0, 0),
                LocalDateTime.of(2050, 3, 10, 0, 0, 0),
                0));
        food.add(new Cheese(10,
                LocalDateTime.of(2018, 3, 1, 0, 0, 0),
                LocalDateTime.of(2019, 2, 10, 0, 0, 0),
                0));
        return food;
    }

    public void run() {
        List<Food> food = delivery();
        sorters.forEach(iSorter -> {
            cq.setSorter(iSorter);
            cq.sort(food);
        });
    }

    public static void main(String[] args) {
        ControllQuality cq = new ControllQuality();
        FoodBusiness fb = new FoodBusiness(cq);
        fb.init();
        fb.run();
        fb.storage.values().forEach(s -> {
            System.out.println(s.getStorage().size() + " items are in" + s);
        });
    }

}
