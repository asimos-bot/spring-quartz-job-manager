package br.com.quartz.manager;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JobManager {

    public void createJob(Scanner scanner, Scheduler sch) {

        System.out.println("nome do job:");
        String name = scanner.next();
        System.out.println("Em quantos segundos o job vai rodar:");
        int startDelay = scanner.nextInt();
        System.out.println("Por quantos segundos ele vai rodar:");
        int duration = scanner.nextInt();
        System.out.println("Intervalo de tempo entre execuções:");
        int interval = scanner.nextInt();
        JobDetail job = jobDetail(name);
        Trigger trigger = trigger(job, startDelay, interval);
        try {
            sch.scheduleJob(job, trigger);
            sch.getJobDetail(job.getKey()).getJobDataMap().put("argumento1",duration);
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Bean
	public JobDetail jobDetail(String name) {
    	return JobBuilder.newJob().ofType(ExampleJob.class)
			.storeDurably()
			.withIdentity(name)  
			.withDescription("Exemplo de Job")
			.build();
	}

	@Bean
	public Trigger trigger(JobDetail job, int delay, int interval) {
		return TriggerBuilder.newTrigger().forJob(job)
			.withIdentity("Qrtz_Trigger")
			.withDescription("Sample trigger")
			.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever())
			.startAt(new Date(LocalDateTime.now().getSecond() + delay))
			.build();
	}
}
