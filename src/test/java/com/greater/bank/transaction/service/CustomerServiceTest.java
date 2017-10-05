package com.greater.bank.transaction.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.greater.bank.transaction.config.AppConfiguration;
import com.greater.bank.transaction.model.Transaction;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public class CustomerServiceTest {
	@Autowired
	CustomerService customerService;


	@Test
	public void appyTransactionFileTest() {
		Transaction t = new Transaction(1, -10);
		this.customerService.applyTransaction(t);

		Assert.assertEquals(10.0, this.customerService.getAccountBanlance(1), 0.0);
		// increase balance
		t = new Transaction(1, -10);
		this.customerService.applyTransaction(t);
		Assert.assertEquals(20.0, this.customerService.getAccountBanlance(1), 0.0);
		// decrease balance
		t = new Transaction(1, 10);
		this.customerService.applyTransaction(t);
		Assert.assertEquals(10.0, this.customerService.getAccountBanlance(1), 0.0);
	}

	@Test(expected = NullPointerException.class)
	public void NullPointExceptionTest() {

		this.customerService.getAccountBanlance(-100);
	}

}
