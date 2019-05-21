package ru.job4j.service;

import ru.job4j.persistence.DbHall;
import ru.job4j.persistence.IHall;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service implements IService<Seat, Account> {
    private static final IService INSTANCE = new Service();
    private final IHall<Seat, Account> hall = DbHall.getInstance();

    private Service() {
    }

    public static IService getInstance() {
        return INSTANCE;
    }

    public Map<Integer, List<Seat>> getAll() {
        return hall.getAll().stream().collect(Collectors.groupingBy(Seat::getRow));
    }

    public Seat createSeat(final int id, final int number, final int row) {
        return new Seat(id, number, row);
    }

    public Seat reserve(Seat seat) {
        return hall.reserve(seat);
    }

    public Seat getSeat(Seat seat) {
        return hall.getSeat(seat);
    }

    @Override
    public Account buy(Account account) {
        return hall.buy(account);
    }

}
