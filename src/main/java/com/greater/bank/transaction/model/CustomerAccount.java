package com.greater.bank.transaction.model;

public class CustomerAccount {

	public CustomerAccount(Integer customerAccount) {
		super();
		this.customerAccount = customerAccount;
	}

	private Integer customerAccount = 0;
	private Double banlance = 0D;

	public void apply(double transactionAmount) {
		if (transactionAmount > 0) {
			this.credit(transactionAmount);
		} else {
			this.debit(transactionAmount);
		}
	}

	public Integer getCustomerAccount() {
		return this.customerAccount;
	}

	public void setCustomerAccount(Integer customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Double getBanlance() {
		return this.banlance;
	}

	private void debit(double amount) {
		this.banlance += Math.abs(amount);
	}

	private void credit(double amount) {
		this.banlance -= Math.abs(amount);
	}

}
