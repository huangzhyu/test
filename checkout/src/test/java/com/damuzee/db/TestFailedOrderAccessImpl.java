package com.damuzee.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.damuzee.common.Utils;
import com.damuzee.model.FailedOrder;

public class TestFailedOrderAccessImpl {
	
	ApplicationContext context = null;
	FailedOrderAccessImpl orderAccess;
    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("application-context.xml");
        orderAccess = context.getBean(FailedOrderAccessImpl.class);
    }

	@Test
	public void testAddFailedOrder(){
		FailedOrder order = new FailedOrder();
		order.setOrderId("14468118502374438320");
		order.setTime(Utils.getCurrentTime());
		order.setType((byte) 0);
		order.setStatus((byte) 1);
		Assert.assertTrue(orderAccess.add(order));
		FailedOrder forder = new FailedOrder();
		forder.setStatus((byte) 1);
		getAllFailedOrder(forder);
	}
	
	private void getAllFailedOrder(FailedOrder order){
		Assert.assertTrue(!orderAccess.getALL(order).isEmpty());
	}
}
