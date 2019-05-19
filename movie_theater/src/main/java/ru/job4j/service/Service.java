package ru.job4j.service;

import ru.job4j.persistence.DbHall;
import ru.job4j.persistence.IHall;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service implements IService<Seat> {
    private static final IService INSTANCE = new Service();
    private final IHall hall = DbHall.getInstance();

    private Service() {

    }

    public static IService getInstance() {
        return INSTANCE;
    }

    public Map<Integer, List<Seat>> getAll() {
        return (Map<Integer, List<Seat>>) hall.getAll().stream().collect(Collectors.groupingBy(Seat::getRow));
    }

    public Seat createSeat(final int number, final int row) {
        return new Seat(number, row);
    }

    public Seat reserve(Seat seat) {
        return (Seat) hall.reserve(seat);
    }

}
