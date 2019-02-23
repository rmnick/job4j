package ru.job4j;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuarztJob implements Job {
    private final String text;

    public QuarztJob(final String text) {
        this.text = text;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(text);
    }
}
