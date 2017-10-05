package com.greater.bank.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greater.bank.transaction.config.AppConfiguration;
import com.greater.bank.transaction.quartz.Scheduling;


public class GreaterBankMain {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		Scheduling scheduling = context.getBean(Scheduling.class);
		scheduling.run();


	}


}
