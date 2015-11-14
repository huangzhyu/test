package com.damuzee.executor;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestScheduledExecutor {
	ApplicationContext context = null;
	ScheduledExecutor scheduled;

	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext("application-context.xml");
		scheduled = context.getBean(ScheduledExecutor.class);
	}
	/**
	 * make sure that there exist failed order in unsuccess table
	 */
	@Test
	public void testScheduledTask(){
		scheduled.setDelaySeconds(10);
		scheduled.start();
		for(;;){}
	}
}
