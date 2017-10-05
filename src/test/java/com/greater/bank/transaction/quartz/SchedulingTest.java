package com.greater.bank.transaction.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.greater.bank.transaction.config.AppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public class SchedulingTest {
	@Autowired
	Scheduling sched;
	@Test
	public void testS() {
		this.sched.run();
	}

}
