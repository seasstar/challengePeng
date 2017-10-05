package com.greater.bank.transaction;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.greater.bank.transaction.config.TransactionProperties;
import com.greater.bank.transaction.model.TransactionsFile;
import com.greater.bank.transaction.parser.TransactionsFileParser;
import com.greater.bank.transaction.service.CustomerService;
import com.greater.bank.transaction.utils.FileUtils;



public class TransactionProcessor {

	private static final Logger log = LoggerFactory.getLogger(TransactionProcessor.class);

	@Autowired
	private TransactionProperties transactionProperties;

	@Autowired
	private CustomerService customerService;

	public void execute() {
		// Read all pending transaction files
		List<TransactionsFile> transactionFiles = this.readPendingFiles();

		for (TransactionsFile file : transactionFiles) {
			// Apply transactions from to customer accounts

			this.customerService.appyTransactionFile(file);

			// Write the report, then archive the file
			this.writeReport(file);
			this.archiveFile(file);
		}
	}

	private List<TransactionsFile> readPendingFiles() {
		TransactionProcessor.log.info("Loading pending transactions");
		List<TransactionsFile> transactionFiles = new ArrayList<>();
		List<Path> paths = FileUtils.INSTANCE.listFiles(this.transactionProperties.getPendingDir());

		for (Path path : paths) {

			TransactionsFile tf = TransactionsFileParser.INSTANCE.fromPath(path);
			transactionFiles.add(tf);
		}

		return transactionFiles;
	}

	private void writeReport(TransactionsFile file) {
		String filename = file.getFileName();

		String datetime = filename.replace("finance_customer_transactions-", "").replace(".csv", "");
		Path path = Paths.get(this.transactionProperties.getReportsDir(), "finance_customer_transactions_report-" + datetime + ".txt");

		TransactionProcessor.log.info("Writing report to {}", path);
		FileUtils.INSTANCE.writeFile(path, file.generateReport());
	}

	private void archiveFile(TransactionsFile file) {
		TransactionProcessor.log.info("Archiving transaction file {}", file.getFileName());
		Path from = Paths.get(this.transactionProperties.getPendingDir() + "/" + file.getFileName());
		Path to = Paths.get(this.transactionProperties.getArchiveDir() + "/" + file.getFileName());
		FileUtils.INSTANCE.moveFile(from, to);
	}

	public TransactionProperties getTransactionProperties() {
		return this.transactionProperties;
	}

	public void setTransactionProperties(TransactionProperties transactionProperties) {
		this.transactionProperties = transactionProperties;
	}


}
