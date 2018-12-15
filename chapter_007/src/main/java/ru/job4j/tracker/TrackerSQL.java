package ru.job4j.tracker;

import ru.job4j.start.ITracker;
import ru.job4j.start.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.function.Predicate;

public class TrackerSQL implements ITracker, AutoCloseable {
    private Connection connection;
    private Properties config;

    public TrackerSQL() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            this.config = new Properties();
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
        String checkUpdate = "create table if not exists items(id serial primary key, name varchar(200), description varchar(500), date timestamp);";
        try (PreparedStatement st = connection.prepareStatement(checkUpdate)) {
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean init() {
        return this.connection != null;
    }

    @Override
    public Item addItem(Item item) {
        Item result = null;
        String add = "insert into items(name, description, date) values(?, ?, ?);";
        try (PreparedStatement st = connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setTimestamp(3, new Timestamp(item.getDate()));
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (!rs.next()) {
                throw new NoSuchElementException();
            }
            int id = (rs.getInt(1));
            item.setId(String.valueOf(id));
            result = item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        String update = "update items set name = ?, description = ?, date = ? where id = ?;";
        try (PreparedStatement st = connection.prepareStatement(update)) {
            Integer valueOfId = Integer.valueOf(id);
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setTimestamp(3, new Timestamp(item.getDate()));
            st.setInt(4, valueOfId);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Integer valueOfId = null;
        try {
            valueOfId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            return false;
        }
        String select = String.format("select count(id) from items where id = %d;", valueOfId);
        String delete = String.format("delete from items where id = %d;", valueOfId);
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(select);
            if (rs.next()) {
                if (rs.getInt(1) != 0) {
                    st.execute(delete);
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        String select = "select i.id, i.name, i.description, i.date from items as i;";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                Timestamp date = rs.getTimestamp(4);
                Item item = new Item(name, description);
                item.setId(String.valueOf(id));
                item.setDate(date.getTime());
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Item> findByName(Predicate<String> predicate) {
        List<Item> list = new ArrayList<>();
        String select = "select i.id, i.name, i.description, i.date from items as i;";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                if (predicate.test(rs.getString(2))) {
                    Item item = new Item(rs.getString(2), rs.getString(3));
                    Timestamp date = rs.getTimestamp(4);
                    item.setId(String.valueOf(rs.getInt(1)));
                    item.setDate(date.getTime());
                    list.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item findById(Predicate<String> predicate) {
        Item item = null;
        String select = "select i.id, i.name, i.description, i.date from items as i;";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                if (predicate.test(String.valueOf(rs.getInt(1)))) {
                    item = new Item(rs.getString(2), rs.getString(3));
                    Timestamp date = rs.getTimestamp(4);
                    item.setId(String.valueOf(rs.getInt(1)));
                    item.setDate(date.getTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * for tests
     */
    public void clearDB() {
        String drop = "drop table items;";
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
