package ru.job4j;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class StartParsing {

    private static final Logger LOG = LogManager.getLogger(StartParsing.class.getName());

    public static void main(String[] args) {
        LOG.info("start scheduler");
        JobDetail job = JobBuilder.newJob(QuartzJob.class).build();
        Trigger tr = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
        try {
            Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
            sc.start();
            sc.scheduleJob(job, tr);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage());
        }
    }
}
