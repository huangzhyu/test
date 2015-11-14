package com.damuzee.db;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.damuzee.model.Integral;
import com.damuzee.model.ResultHolder;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestIntegralAccessImpl {
	ApplicationContext context = null;
	 IntegralAccessImpl impl=null;
    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("application-context.xml");
        impl= context.getBean(IntegralAccessImpl.class);
    }
    
	@Test
	public void testAddIntegral(){
	   
	    Integral integral = Mockito.mock(Integral.class);
	    Mockito.when(integral.getUserId()).thenReturn(Math.random()+"");
	    Mockito.when(integral.getCount()).thenReturn(250L);
	    Mockito.when(integral.getOrderId()).thenReturn("orderid");
	    Mockito.when(integral.getRatio()).thenReturn(50);
	    Mockito.when(integral.getType()).thenReturn((byte) 0);
	    Mockito.when(integral.getTime()).thenReturn(new Timestamp(System.currentTimeMillis()));
	    
	    Integral integral2 = Mockito.mock(Integral.class);
        Mockito.when(integral2.getUserId()).thenReturn(Math.random()+"");
        Mockito.when(integral2.getCount()).thenReturn(150L);
        Mockito.when(integral2.getOrderId()).thenReturn("orderid");
        Mockito.when(integral2.getRatio()).thenReturn(30);
        Mockito.when(integral2.getType()).thenReturn((byte) 0);
        Mockito.when(integral2.getTime()).thenReturn(new Timestamp(System.currentTimeMillis()));
        
        Integral integral3 = Mockito.mock(Integral.class);
        Mockito.when(integral3.getUserId()).thenReturn(Math.random()+"");
        Mockito.when(integral3.getCount()).thenReturn(100L);
        Mockito.when(integral3.getOrderId()).thenReturn("orderid");
        Mockito.when(integral3.getRatio()).thenReturn(20);
        Mockito.when(integral3.getType()).thenReturn((byte) 0);
        Mockito.when(integral3.getTime()).thenReturn(new Timestamp(System.currentTimeMillis()));
        
        ArrayList<Integral> integrals = new ArrayList<Integral>();
        integrals.add(integral);
        integrals.add(integral2);
        integrals.add(integral3);
        
        ResultHolder holder = new ResultHolder();
        holder.setIntegrals(integrals);
	    
		Assert.assertTrue(impl.add(holder));
	}
	
	@Test
	public void testGetTotalIntegral(){
		ResultHolder holder = new ResultHolder();
		holder.setUserId("0.467474560120158");
		long count = impl.getSumIntegral(holder);
		Assert.assertEquals(150, count);
	}
}
