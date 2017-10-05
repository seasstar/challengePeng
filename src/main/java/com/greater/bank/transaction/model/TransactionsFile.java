package com.greater.bank.transaction.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionsFile {
	private String fileName;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	private Integer numSkippedLines = 0;

	public TransactionsFile(String fileName) {
		super();
		this.fileName = fileName;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void addTransaction(Transaction transaction) {
		if (transaction == null) {
			this.numSkippedLines++;
			return;
		}

		this.transactions.add(transaction);
	}

	public Integer getNumSkippedLines() {
		return this.numSkippedLines;
	}

	public Long getNumAccountsProcessed() {
		return this.transactions.stream().map(Transaction::getCustomerAccount).distinct().count();
	}


	public Double getTotalCredits() {
		return this.transactions.stream().filter(t -> t.getTransactionAmount() > 0).mapToDouble(Transaction::getTransactionAmount).sum();
	}


	public Double getTotalDebits() {
		return this.transactions.stream().filter(t -> t.getTransactionAmount() < 0).mapToDouble(t -> Math.abs(t.getTransactionAmount())).sum();
	}

	public String generateReport() {
		return "File Processed: " + this.fileName + "\nTotal Accounts:" + String.format("%,d", this.getNumAccountsProcessed()) + "\nTotal Credits : " + String.format("$%,.2f", this.getTotalCredits()) + "\nTotal Debits  : " + String.format("$%,.2f", this.getTotalDebits()) + "\nSkipped Transactions: " + this.getNumSkippedLines();
	}

	public String getFileName() {
		return this.fileName;
	}


}
