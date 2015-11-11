package com.damuzee.facade.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.damuzee.db.IntegralDataAccessImpl;
import com.damuzee.db.TaskDAO;
import com.damuzee.facade.CheckoutFacade;
import com.damuzee.model.Config;
import com.damuzee.model.Integral;
import com.damuzee.model.Task;

public class CheckoutFacadeImplTest {
    ApplicationContext context = null;
    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }
    

	@Test
    public void testCheckout() {
        final CheckoutFacade impl = context.getBean("checkoutFacade", CheckoutFacade.class);
        final CountDownLatch counter = new CountDownLatch(1);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try {
                        counter.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Task t = new Task();
                    t.set_id("0");
                    impl.checkout(t);
                }
            });
            threads[i].start();

        }

        counter.countDown();
        try {
            for(Thread t : threads){
                t.join();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("done");
        for(;;){
            Thread.yield();
        }
    }
	
	
	@Test
	public void testConfig(){
	    Config config = context.getBean(Config.class);
	    System.out.println(config.getSelfRatio());
	    System.out.println(config.getFirstLevelRatio());
	    System.out.println(config.getSecondLevelRatio());
	}
	
	@Test
	public void testAdd(){
	    IntegralDataAccessImpl impl = context.getBean(IntegralDataAccessImpl.class);
	    Integral integral = Mockito.mock(Integral.class);
	    Mockito.when(integral.getUserId()).thenReturn(String.valueOf(System.currentTimeMillis()));
	    Mockito.when(integral.getCount()).thenReturn(250);
	    Mockito.when(integral.getOrderId()).thenReturn("orderid");
	    Mockito.when(integral.getRatio()).thenReturn(50);
	    Mockito.when(integral.getType()).thenReturn((byte) 0);
	    Mockito.when(integral.getTime()).thenReturn(new Timestamp(System.currentTimeMillis()));
	    
	    Integral integral2 = Mockito.mock(Integral.class);
        Mockito.when(integral2.getUserId()).thenReturn(String.valueOf(System.currentTimeMillis()));
        Mockito.when(integral2.getCount()).thenReturn(150);
        Mockito.when(integral2.getOrderId()).thenReturn("orderid");
        Mockito.when(integral2.getRatio()).thenReturn(30);
        Mockito.when(integral2.getType()).thenReturn((byte) 0);
        Mockito.when(integral2.getTime()).thenReturn(new Timestamp(System.currentTimeMillis()));
        
        Integral integral3 = Mockito.mock(Integral.class);
        Mockito.when(integral3.getUserId()).thenReturn(String.valueOf(System.currentTimeMillis()));
        Mockito.when(integral3.getCount()).thenReturn(100);
        Mockito.when(integral3.getOrderId()).thenReturn("orderid");
        Mockito.when(integral3.getRatio()).thenReturn(20);
        Mockito.when(integral3.getType()).thenReturn((byte) 0);
        Mockito.when(integral3.getTime()).thenReturn(new Timestamp(System.currentTimeMillis()));
        
        ArrayList<Integral> parents = new ArrayList<Integral>();
        parents.add(integral);
        parents.add(integral2);
        
        Mockito.when(integral3.getParents()).thenReturn(parents);
	    
	    impl.add(integral3);
	}
}
