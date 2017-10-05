package com.greater.bank.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.greater.bank.transaction.TransactionProcessor;
import com.greater.bank.transaction.quartz.Scheduling;
import com.greater.bank.transaction.service.CustomerService;

/**
 * Spring Bean config
 * 
 * @author Peng Wang
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.greater.bank.transaction.*" })
public class AppConfiguration {
	@Bean
	public TransactionProperties getTransactionProperties() {
		return new TransactionProperties();
	}
	@Bean
	public TransactionProcessor getTransactionProcessor() {
		return new TransactionProcessor();
	}
	@Bean
	public Scheduling getSchedulling() {
		return new Scheduling();
	}

	@Bean
	public CustomerService getCustomerService() {
		return new CustomerService();
	}
}
