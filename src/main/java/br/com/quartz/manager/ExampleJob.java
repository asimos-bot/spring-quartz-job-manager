package br.com.quartz.manager;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ExampleJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(ExampleJob.class);

    @Override
    public void execute(JobExecutionContext context) {

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        log.info("Job chamado com argumento de valor " + dataMap.getInt("argumento1"));
        try {
            Thread.sleep(Long.valueOf(dataMap.getInt("argumento1")));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Job terminou");
    }
}
