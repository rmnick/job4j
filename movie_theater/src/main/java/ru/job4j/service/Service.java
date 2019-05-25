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

    /**
     * return Map with all seats as values and integers(serial numbers) as rows from DB
     * @return Map<Integer, List<Seat>>
     */
    public Map<Integer, List<Seat>> getAll() {
        return hall.getAll().stream().collect(Collectors.groupingBy(Seat::getRow));
    }

    /**
     * create Seat
     * @param id int
     * @param number int
     * @param row int
     * @return Seat
     */
    public Seat createSeat(final int id, final int number, final int row) {
        return new Seat(id, number, row);
    }

    /**
     * reserve seat for a certain time(session timeout)
     * @param seat Seat
     * @return Seat
     */
    public Seat reserve(Seat seat) {
        return hall.reserve(seat);
    }

    /**
     * cancel reservation for chosen seat
     * @param seat Seat
     * @return Seat
     */
    public Seat cancelReservation(Seat seat) {
        return hall.cancelReservation(seat);
    }

    /**
     * return seat from DB according seat id
     * @param seat Seat
     * @return Seat
     */
    public Seat getSeat(Seat seat) {
        return hall.getSeat(seat);
    }

    /**
     * return account from DB
     * @param account Account
     * @return Account
     */
    public Account getAccount(Account account) {
        return hall.getAccount(account);
    }

    /**
     * buy ticket according account and seat id
     * @param account Account
     * @return Account
     */
    @Override
    public Account buy(Account account) {
        Account result;
        if (hall.getAccount(account) == null) {
            result = hall.createBuy(account);
        } else {
            result = hall.bindBuy(account);
        }
        return result;
    }

}
