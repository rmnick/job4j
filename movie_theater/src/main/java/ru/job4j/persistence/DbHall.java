package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.service.Seat;
import ru.job4j.service.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbHall implements IHall<Seat> {
    private final static BasicDataSource SOURCE = new BasicDataSource();
    private static final IHall INSTANCE = new DbHall();
    public static final Logger LOG = LogManager.getLogger(DbHall.class.getName());


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
//                seat.setId(id);
                seat.setPrice(price);
                result.add(seat);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public Seat reserve(Seat seat) {
        String update = String.format("update hall set booked = true where id = %d", seat.getId());
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement()) {
            st.execute(update);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return seat;
    }

    public Seat getSeat(Seat seat) {
        String select = String.format("select h.id, h.row, h.number, h.price from hall as h where h.id = %d;", seat.getId());
        Seat result = null;
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                int row = rs.getInt(2);
                int number = rs.getInt(3);
                int price = rs.getInt(4);
                result = (Seat) Service.getInstance().createSeat(id, row, number);
                result.setPrice(price);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

}
