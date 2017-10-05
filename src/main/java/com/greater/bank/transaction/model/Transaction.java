package com.greater.bank.transaction.model;

public class Transaction {

	public Transaction(int customerAccount, double transactionAmount) {
		super();
		this.customerAccount = customerAccount;
		this.transactionAmount = transactionAmount;
	}

	private int customerAccount;
	private double transactionAmount;

	public int getCustomerAccount() {
		return this.customerAccount;
	}

	public void setCustomerAccount(int customerAccount) {
		this.customerAccount = customerAccount;
	}

	public double getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

}
