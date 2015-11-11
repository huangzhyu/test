package com.damuzee.facade.impl;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.damuzee.db.TaskDAO;
import com.damuzee.facade.CheckoutFacade;
import com.damuzee.model.Config;
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
		TaskDAO task = (TaskDAO) context.getBean("task");
		task.add();
	}
}
