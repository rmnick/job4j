package ru.job4j.servlets.users.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlets.users.logic.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Properties;

public class DbStore implements IStore<User> {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("app.properties")) {
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
            e.printStackTrace();
        }
        createStructure();
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    private void createStructure() {
        String init = "create table if not exists tb_user(id serial primary key, name varchar(50), login varchar(50), email varchar(50), create_date timestamp);";
        try (Statement st = SOURCE.getConnection().createStatement()) {
            st.execute(init);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User add(User user) {
        User result = null;
        String insert = "insert into tb_user (name, login, email, create_date) values (?, ?, ?, ?)";
        try (PreparedStatement ps = SOURCE.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            LocalDateTime date = LocalDateTime.now();
            ps.setTimestamp(4, Timestamp.valueOf(date));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                throw new NoSuchElementException();
            }
            String id = String.valueOf(rs.getInt(1));

            user.setId(id);
            user.setDate(date);
            result =  user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User delete(User user) {
        User result = null;
        String delete = String.format("delete from tb_user where id=%d", (int) Integer.valueOf(user.getId()));
        try (Statement st = SOURCE.getConnection().createStatement()) {
            result = this.getUser(user);
            st.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User update(User user) {
        User result = null;
        String update = String.format("update tb_user set name=?, login=?, email=? where id=%d", (int) Integer.valueOf(user.getId()));
        try (PreparedStatement ps = SOURCE.getConnection().prepareStatement(update)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            result = user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User getUser(User user) {
        User result = null;
        String select = String.format("select * from tb_user where id=%d", (int) Integer.valueOf(user.getId()));
        try (Statement st = SOURCE.getConnection().createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                String name = rs.getString(2);
                String login = rs.getString(3);
                String email = rs.getString(4);
                LocalDateTime date = rs.getTimestamp(5).toLocalDateTime();
                user.setName(name);
                user.setLogin(login);
                user.setEmail(email);
                user.setDate(date);
                result = user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
