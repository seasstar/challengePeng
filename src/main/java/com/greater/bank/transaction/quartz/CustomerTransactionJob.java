package com.greater.bank.transaction.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greater.bank.transaction.TransactionProcessor;
import com.greater.bank.transaction.config.AppConfiguration;


public class CustomerTransactionJob implements Job {

	public static final String JOB_NAME = "TransactionProcessor";
	public static final String GROUP_NAME = "TRANSACTION";
	@Autowired
	TransactionProcessor tp;
	private ApplicationContext context;
	@Override
	public void execute(JobExecutionContext con) throws JobExecutionException {
		try {
		this.context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		this.tp = this.context.getBean(TransactionProcessor.class);
		this.tp.execute();
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}

	}
}
