package ru.job4j;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class QuarztJob implements Job {

    private static final Logger LOG = LogManager.getLogger(QuarztJob.class.getName());
    private final BlockingQueue<Vacancy> vacancies = new ArrayBlockingQueue<>(50);
    private final Config config = new Config();
    private PageParser parser = new PageParser(config, vacancies);


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        parser.parse();
    }
}
