package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.service.Account;
import ru.job4j.service.MyException;
import ru.job4j.service.Seat;
import ru.job4j.service.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class DbHall implements IHall<Seat, Account> {
    private final static BasicDataSource SOURCE = new BasicDataSource();
    private static final IHall INSTANCE = new DbHall();
    private static final Logger LOG = LogManager.getLogger(DbHall.class.getName());


    private DbHall() {
        try (InputStream in = DbHall.class.getClassLoader().getResourceAsStream("theatre.properties")) {
            Properties config = new Properties();
            config.load(in);
            SOURCE.setDriverClassName(config.getProperty("driver-class-name"));
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static IHall getInstance() {
        return INSTANCE;
    }

    /**
     * return all seats from DB
     * @return List<Seat>
     */
    @Override
    public List<Seat> getAll() {
        List<Seat> result = new ArrayList<>();
        String select = "select h.id, h.row, h.number, h.booked, h.price from hall as h order by id;";
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                int row = rs.getInt(2);
                int number = rs.getInt(3);
                boolean booked = rs.getBoolean(4);
                int price = rs.getInt(5);
                Seat seat = (Seat) Service.getInstance().createSeat(id, number, row);
                seat.setBooked(booked);
                seat.setPrice(price);
                result.add(seat);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * seat reservation for the time of purchase
     * using "select for update"
     * @param seat Seat
     * @return Seat
     */
    @Override
    public Seat reserve(Seat seat) {
        String selectForUpdate = String.format("select h.id, h.row, h.number, h.price, h.booked, h.id_account from hall as h where h.id = %d and h.booked = false for update;", seat.getId());
        String update = String.format("update hall set booked = true where id = %d;", seat.getId());
        Connection con = null;
        Seat result = null;
        try {
            con = SOURCE.getConnection();
            con.setAutoCommit(false);
            try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(selectForUpdate)) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    int row = rs.getInt(2);
                    int number = rs.getInt(3);
                    int price = rs.getInt(4);
                    boolean booked = rs.getBoolean(5);
                    int accountId = rs.getInt(6);
                    result = (Seat) Service.getInstance().createSeat(id, number, row);
                    result.setPrice(price);
                    result.setBooked(booked);
                    result.setAccountId(accountId);
                    st.execute(update);
                    con.commit();
                    result.setBooked(true);
                    LOG.info(String.format("reserve place with id %d", id));
                }
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            result = null;
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * canceling reservation if session time's out
     * @param seat Seat
     * @return Seat
     */
    @Override
    public Seat cancelReservation(Seat seat) {
        String update = String.format("update hall set booked = false where id = %d;", seat.getId());
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement()) {
            st.execute(update);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return seat;
    }

    /**
     * return Seat from DB, find by seat id
     * @param seat Seat
     * @return Seat
     */
    @Override
    public Seat getSeat(Seat seat) {
        String select = String.format("select h.id, h.row, h.number, h.price, h.booked, h.id_account from hall as h where h.id = %d;", seat.getId());
        Seat result = null;
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                int row = rs.getInt(2);
                int number = rs.getInt(3);
                int price = rs.getInt(4);
                boolean booked = rs.getBoolean(5);
                int accountId = rs.getInt(6);
                result = (Seat) Service.getInstance().createSeat(id, number, row);
                result.setPrice(price);
                result.setBooked(booked);
                result.setAccountId(accountId);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * find and return account from DB, find by phone
     * @param account Account
     * @return Account
     */
    @Override
    public Account getAccount(Account account) {
        String select = String.format("select a.id, a.name, a.phone from accounts as a where a.phone = '%s';", account.getPhone());
        Account result = null;
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                account.setName(name);
                account.setPhone(phone);
                account.setId(id);
                result = account;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * buy ticket if account is already exist, bind seat to account
     * @param account Account
     * @return Account
     */
    @Override
    public Account bindBuy(Account account, Seat seat) {
        Account result;
        String update = String.format("update hall set id_account = %s where id = %s and id_account is null;", account.getId(), seat.getId());
        Connection con = null;
        try {
            con = SOURCE.getConnection();
            con.setAutoCommit(false);
            try (Statement st = con.createStatement()) {
                st.execute(update);
            }
            con.commit();
            LOG.info(String.format("bindBuy account %s ticket %s;", account.getPhone(), seat.getId()));
            result = account;
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            result = null;
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * buy ticket if account doesn't exist, create account, bind seat to account
     * @param account Account
     * @return Account
     */
    @Override
    public Account createBuy(Account account, Seat seat) {
        Account result;
        String insert = "insert into accounts (name, phone) values (?, ?);";
        Connection con = null;
        try {
            con = SOURCE.getConnection();
            con.setAutoCommit(false);
            int id;
            try (PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, account.getName());
                ps.setString(2, account.getPhone());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (!rs.next()) {
                    throw new NoSuchElementException();
                }
                id = rs.getInt(1);
                account.setId(id);
            }
            String update = String.format("update hall set id_account = %d where id = %s and id_account is null;", id, seat.getId());
            try (Statement st = con.createStatement()) {
                st.execute(update);
            }
            con.commit();
            LOG.info(String.format("createBuy account %s ticket %s", account.getPhone(), seat.getId()));
            result = account;
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            result = null;
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * canceling all reservation(if it's not purchased) when DB close
     */
    public void cancelAll() {
        String update = "update hall set booked = false where id_account is null;";
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement()) {
            st.execute(update);
            LOG.info("canceling all items");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        try {
            LOG.info("in close method");
            cancelAll();
            if (SOURCE != null) {
                SOURCE.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
