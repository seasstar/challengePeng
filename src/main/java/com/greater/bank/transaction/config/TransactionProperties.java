package com.greater.bank.transaction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * match with config.properties
 * 
 * @author Peng Wang
 *
 */
@Component
@PropertySource("classpath:config.properties")
public class TransactionProperties {
	@Value("${processing.pendingDir}")
	private String pendingDir;

	@Value("${processing.reportsDir}")
	private String reportsDir;

	@Value("${processing.archiveDir}")
	private String archiveDir;

	@Value("${cronExpression1}")

	private String cronExpression1;
	@Value("${cronExpression2}")
	private String cronExpression2;

	public String getPendingDir() {
		return this.pendingDir;
	}

	public void setPendingDir(String pendingDir) {
		this.pendingDir = pendingDir;
	}

	public String getReportsDir() {
		return this.reportsDir;
	}

	public void setReportsDir(String reportsDir) {
		this.reportsDir = reportsDir;
	}

	public String getArchiveDir() {
		return this.archiveDir;
	}

	public void setArchiveDir(String archiveDir) {
		this.archiveDir = archiveDir;
	}

	public String getCronExpression1() {
		return this.cronExpression1;
	}

	public void setCronExpression1(String cronExpression1) {
		this.cronExpression1 = cronExpression1;
	}

	public String getCronExpression2() {
		return this.cronExpression2;
	}

	public void setCronExpression2(String cronExpression2) {
		this.cronExpression2 = cronExpression2;
	}


}
