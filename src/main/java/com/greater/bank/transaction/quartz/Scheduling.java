package com.greater.bank.transaction.quartz;

import java.util.HashSet;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.greater.bank.transaction.TransactionProcessor;
import com.greater.bank.transaction.config.TransactionProperties;


public class Scheduling {
	private static final Logger log = LoggerFactory.getLogger(Scheduling.class);
	final SchedulerFactory _schedulerFactory;
	@Autowired
	TransactionProperties transactionProperties;
	@Autowired
	TransactionProcessor tp;
	public Scheduling() {
		this._schedulerFactory = new StdSchedulerFactory();
	}

	public void run() {

		try {
			Scheduler sched = this._schedulerFactory.getScheduler();
			Scheduling.log.info("cron expression1:{}", this.transactionProperties.getCronExpression1());
			sched.start();


			String groupName = CustomerTransactionJob.GROUP_NAME;
			String jobName = CustomerTransactionJob.JOB_NAME;
			String triggerName = jobName + "0601pm";
			Set<CronTrigger> triggers = new HashSet<>();
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, groupName);
			JobDetail jobDetail = JobBuilder.newJob(CustomerTransactionJob.class).withIdentity(jobName, groupName).build();
			CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(this.transactionProperties.getCronExpression1())).build();
			triggers.add(newTrigger);
			triggerName = jobName + "2101pm";
			triggerKey = TriggerKey.triggerKey(triggerName, groupName);
			newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(this.transactionProperties.getCronExpression2())).build();
			triggers.add(newTrigger);
			sched.scheduleJob(jobDetail, triggers, false);
			Scheduling.log.info("Scheduled next Fire Time {}", newTrigger.getNextFireTime());

		} catch (SchedulerException e) {
			Scheduling.log.error("Error process Transactions:{}", e);
		}


	}
}
