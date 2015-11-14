package com.damuzee.facade.impl;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.damuzee.common.Checkout;
import com.damuzee.executor.ThreadPoolFactory;
import com.damuzee.facade.CheckoutFacade;
import com.damuzee.model.Config;

public class CheckoutFacadeImplTest {
    ApplicationContext context = null;
    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }
    
    

	@Test
    public void testCheckoutSuccess() {
		int threadNum=10;
		final Random random = new Random();
        final CheckoutFacade impl = context.getBean("checkoutFacade", CheckoutFacade.class);
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try {
						Thread.sleep(random.nextInt(10)*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    impl.checkout("14468118502374438320",Checkout.INCOME);
                }
            });
            threads[i].start();

        }

        try {
            for(Thread t : threads){
                t.join();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        for(;;){
            Thread.yield();
        }
    }
	
	@Test
	public void testGetIntegral(){
		 final CheckoutFacade impl = context.getBean("checkoutFacade", CheckoutFacade.class);
		 System.out.println(impl.getIntegral("5637389a0cf2db91dd4f15be"));
	}
	
	@Test
	public void testConfig(){
	    Config config = context.getBean(Config.class);
	    System.out.println("Self:"+config.getSelfRatio());
	    System.out.println("FinalSuperior:"+config.getFinalSuperiorRatio());
	    System.out.println("Superior:"+config.getSuperiorRatio());
	    System.out.println(config.getBonus());
	    System.out.println(config.getConversion());
	}
	
	@Test
	public void testSingleton(){
	    ThreadPoolFactory tf1=context.getBean(ThreadPoolFactory.class);
	    ThreadPoolFactory tf2=context.getBean(ThreadPoolFactory.class);
	    System.out.println(tf1==tf2);
	}
}
