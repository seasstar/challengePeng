package com.greater.bank.transaction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.greater.bank.transaction.model.CustomerAccount;
import com.greater.bank.transaction.model.Transaction;
import com.greater.bank.transaction.model.TransactionsFile;

@Service("customerService")
public class CustomerService {
	Map<Integer, CustomerAccount> customerAccounts = new HashMap<>();

	public void appyTransactionFile(TransactionsFile file) {

		List<Transaction> transactions = file.getTransactions();
		for (Transaction tran : transactions) {
			this.applyTransaction(tran);
		}
	}

	public void applyTransaction(Transaction transaction) {
		Integer accountNumber = transaction.getCustomerAccount();
		CustomerAccount ca = this.customerAccounts.get(accountNumber);
		if (ca == null) {
			ca = new CustomerAccount(accountNumber);
		}
		ca.apply(transaction.getTransactionAmount());
		this.customerAccounts.put(accountNumber, ca);
	}

	public double getAccountBanlance(Integer accountNumber) throws NullPointerException {
		CustomerAccount ca = this.customerAccounts.get(accountNumber);
		if (ca == null) {
			throw new NullPointerException("Customer account number " + accountNumber + " does not exists");
		}
		return ca.getBanlance().doubleValue();
	}
}
