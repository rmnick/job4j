package ru.job4j.servlets.users.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.users.logic.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class DbStore implements IStore<User>, AutoCloseable {

    private static final BasicDataSource SOURCE = new BasicDataSource();
//    private static final DbStore INSTANCE = new DbStore();
    private static final Logger LOG = LogManager.getLogger(DbStore.class.getName());

    private DbStore() {
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
            LOG.error(e.getMessage(), e);
        }
        createStructure();
        createRoot();
    }

    private static class DbStoreHolder {
        public static final DbStore INSTANCE = new DbStore();
    }

    public static DbStore getInstance() {
        return DbStoreHolder.INSTANCE;
    }

    /**
     * create table in DB if it doesn't exist
     * fields: id(generated in db), name, login, email, create_date(LocalDate);
     */
    private void createStructure() {
        String init = "create table if not exists tb_user(id serial primary key, name varchar(50), login varchar(50), password varchar(15), email varchar(50), create_date timestamp);";
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement()) {
            st.execute(init);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * create root user if doesn't exist for authorization test
     */
    private void createRoot() {
        String select = "select u.id from tb_user as u where login=\'root\'";
        String insert = "insert into tb_user (name, login, password, email, create_date) values (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = SOURCE.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            if (!rs.next()) {
                PreparedStatement ps = con.prepareStatement(insert);
                ps.setString(1, "Root");
                ps.setString(2, "root");
                ps.setString(3, "root");
                ps.setString(4, "root@mail.ru");
                LocalDateTime date = LocalDateTime.now();
                ps.setTimestamp(5, Timestamp.valueOf(date));
                ps.executeUpdate();
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * method add. generates id in DB, generates create_date in method
     * if problems with the database this returns null
     * @param user User
     * @return User
     */
    @Override
    public User add(User user) {
        User result = null;
        String insert = "insert into tb_user (name, login, password, email, create_date) values (?, ?, ?, ?, ?)";
        try (Connection con = SOURCE.getConnection(); PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            LocalDateTime date = LocalDateTime.now();
            ps.setTimestamp(5, Timestamp.valueOf(date));
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
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * method delete.
     * if problems with the database this returns null
     * @param user User
     * @return User
     */
    @Override
    public User delete(User user) {
        User result = null;
        String delete = String.format("delete from tb_user where id=%d", (int) Integer.valueOf(user.getId()));
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement()) {
            result = this.getUser(user);
            st.execute(delete);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * method update
     * if problems with the database this returns null
     * @param user User
     * @return User
     */
    @Override
    public User update(User user) {
        User result = null;
        String update = String.format("update tb_user set name=?, login=?, password=?, email=? where id=%d", (int) Integer.valueOf(user.getId()));
        try (Connection con = SOURCE.getConnection(); PreparedStatement ps = con.prepareStatement(update)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            result = user;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * use this method for authentication in session mode
     * unique login is session attribute
     * @param user User
     * @return User
     */
    public User getUserByLogin(User user) {
        User result = null;
        String select = String.format("select u.id, u.name, u.login, u.password, u.email, u.create_date from tb_user as u where u.login=\'%s\'", user.getLogin());
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                String id = Integer.valueOf(rs.getInt(1)).toString();
                String name = rs.getString(2);
                String login = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);
                LocalDateTime date = rs.getTimestamp(6).toLocalDateTime();
                result = new User(name, login, password, email);
                result.setId(id);
                result.setDate(date);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * returns the user from DB by id
     * @param user User
     * @return User
     */
    public User getUser(User user) {
        User result = null;
        String select = String.format("select u.name, u.login, u.password, u.email, u.create_date from tb_user as u where u.id=%d", (int) Integer.valueOf(user.getId()));
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                String name = rs.getString(1);
                String login = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);
                LocalDateTime date = rs.getTimestamp(5).toLocalDateTime();
                result = new User(name, login, password, email);
                result.setId(user.getId());
                result.setDate(date);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * return list of users for root user
     * @return ArrayList<User>
     */
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String select = "select u.id, u.name, u.login, u.password, u.email, u.create_date from tb_user as u";
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String login = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);
                LocalDateTime date = rs.getTimestamp(6).toLocalDateTime();
                User user = new User(name, login, password, email);
                user.setId(id);
                user.setDate(date);
                list.add(user);
            }
        } catch (SQLException e) {
           LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * check password
     * @param user User
     * @return boolean
     */
    public boolean authenticate(User user) {
        boolean result = false;
        String select = String.format("select u.password from tb_user as u where u.login=\'%s\'", user.getLogin());
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            if (rs.next()) {
                String password = rs.getString(1);
                if (password.equals(user.getPassword())) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * check if email already exists
     * @param user User
     * @return boolean
     */
    public boolean compareEmail(User user) {
        boolean result = false;
        String select = String.format("select u.id from tb_user as u where u.email = \'%s\'", user.getEmail());
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * check if login already exists and return true
     * @param user User
     * @return boolean
     */
    public boolean compareLogin(User user) {
        boolean result = false;
        String select = String.format("select u.id from tb_user as u where u.login=\'%s\'", user.getLogin());
        try (Connection con = SOURCE.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(select)) {
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() {
        try {
            if (SOURCE != null) {
                SOURCE.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
