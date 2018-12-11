package ru.job4j.tracker;

import ru.job4j.start.ITracker;
import ru.job4j.start.Item;

import java.io.InputStream;
import java.lang.reflect.Executable;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Predicate;

public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Random RN = new Random();
    private Connection connection;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item addItem(Item item) {
        Item result = null;
        String add = "insert into items(name, description, date) values(?, ?, ?);";
        PreparedStatement st = null;
        try {
            if (connection == null) {
                this.init();
            }
            st = connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setTimestamp(3, new Timestamp(item.getDate()));
            st.executeUpdate();
            ResultSet key = st.getGeneratedKeys();
            int id = 0;
            while (key.next()) {
                id = (key.getInt(1));
            }
            item.setId(String.valueOf(id));
            System.out.println(id);
            st.close();
            result = item;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean replace(String id, Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public List<Item> findByName(Predicate<String> predicate) {
        return null;
    }

    @Override
    public Item findById(Predicate<String> predicate) {
        return null;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
