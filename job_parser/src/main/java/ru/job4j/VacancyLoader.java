package ru.job4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public class VacancyLoader extends Thread {

    private static final Logger LOG = LogManager.getLogger(VacancyLoader.class.getName());
    private BlockingQueue<Vacancy> vacancies;
    private final Connection connection;

    public VacancyLoader(final Connection connection, final BlockingQueue vacancies) {
        this.connection = connection;
        this.vacancies = vacancies;
    }

    public void loadDb(Vacancy vacancy) {
        String insert = "insert into vacancies(name, url, description) values(?, ?, ?);";
        try(PreparedStatement ps = connection.prepareStatement(insert)) {
            ps.setString(1, vacancy.getName());
            ps.setString(2, vacancy.getUrl());
            ps.setString(3, vacancy.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * loading to DB from BlockingQueue. stops when takes the "poison pill"
     */
    @Override
    public void run() {
        try {
            while (true) {
                LOG.info("start loading to DB");
                Vacancy vacancy = this.vacancies.take();
                if (vacancy.getName().equals("stop")) {
                    break;
                }
                loadDb(vacancy);
            }
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        }
        LOG.info("thread close");
    }
}
