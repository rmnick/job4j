package ru.job4j.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Calculation {

    public List<Double> diapason(int start, int end, Function<Integer, Double> function) {
        List<Double> list = new ArrayList<>();
        for (int index = start; index <= end; index++) {
            list.add(function.apply(index));
        }
        return list;
    }

    public double log(int x, int base) {
        return Math.log(x) / Math.log(base);
    }

    /**
     * for example and logarithmic test
     * @param list
     * @param log
     */
    public void doExample(List<Double> list, BiFunction<Integer, Integer, Double> log) {
        for (int i = 1; i <= 4; i++) {
            list.add(log.apply(i, 2));
        }
    }
}
