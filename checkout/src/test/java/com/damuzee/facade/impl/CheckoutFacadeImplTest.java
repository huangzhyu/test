package com.damuzee.facade.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.damuzee.mongo.MongoTemplate;
import org.damuzee.mongo.SimpleMapObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.damuzee.common.Checkout;
import com.damuzee.common.Utils;
import com.damuzee.db.FailedOrderAccessImpl;
import com.damuzee.db.IntegralAccessImpl;
import com.damuzee.db.MemberAccessImpl;
import com.damuzee.executor.ThreadPoolFactory;
import com.damuzee.facade.CheckoutFacade;
import com.damuzee.model.Config;
import com.damuzee.model.FailedOrder;
import com.damuzee.model.Integral;
import com.damuzee.model.Member;
import com.damuzee.model.ResultHolder;

public class CheckoutFacadeImplTest {
    ApplicationContext context = null;
    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }
    
    @Test
    public void testGetMember(){
        MemberAccessImpl ma = context.getBean(MemberAccessImpl.class);
        Member member = ma.getFirst(new Member("14468118502374438320"));
        System.out.println(member);
    }
    
    @Test
    public void getSuperMember(){
        MemberAccessImpl ma = context.getBean(MemberAccessImpl.class);
        Member member = ma.getFirst(new Member("14468118502374438320"));
        System.out.println(member);
        Member result=ma.getSuperiorMember(member);
        System.out.println(result);
        Member result2=ma.getSuperiorMember(result);
        System.out.println(result2);
    }
    

	@Test
    public void testCheckoutSuccess() {
		int threadNum=1;
        final CheckoutFacade impl = context.getBean("checkoutFacade", CheckoutFacade.class);
        final CountDownLatch counter = new CountDownLatch(1);
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try {
                        counter.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    impl.checkout("14468118502374438320",Checkout.INCOME);
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
        
        for(;;){
            Thread.yield();
        }
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
	public void testAddFailedOrder(){
		FailedOrderAccessImpl orderAccess = context.getBean(FailedOrderAccessImpl.class);
		FailedOrder order = new FailedOrder();
		order.setOrderId("14468118502374438320");
		order.setTime(Utils.getCurrentTime());
		order.setType((byte) 0);
		order.setStatus((byte) 1);
		orderAccess.add(order);
	}
	
	@Test
	public void getAllFailedOrder(){
		FailedOrderAccessImpl orderAccess = context.getBean(FailedOrderAccessImpl.class);
		FailedOrder order = new FailedOrder();
		order.setStatus((byte) 1);
		System.out.println(orderAccess.getALL(order));
	}
	
	@Test
	public void testAddIntegral(){
	    IntegralAccessImpl impl = context.getBean(IntegralAccessImpl.class);
	    Integral integral = Mockito.mock(Integral.class);
	    Mockito.when(integral.getUserId()).thenReturn(Math.random()+"");
	    Mockito.when(integral.getCount()).thenReturn(250);
	    Mockito.when(integral.getOrderId()).thenReturn("orderid");
	    Mockito.when(integral.getRatio()).thenReturn(50);
	    Mockito.when(integral.getType()).thenReturn((byte) 0);
	    Mockito.when(integral.getTime()).thenReturn(new Timestamp(System.currentTimeMillis()));
	    
	    Integral integral2 = Mockito.mock(Integral.class);
        Mockito.when(integral2.getUserId()).thenReturn(Math.random()+"");
        Mockito.when(integral2.getCount()).thenReturn(150);
        Mockito.when(integral2.getOrderId()).thenReturn("orderid");
        Mockito.when(integral2.getRatio()).thenReturn(30);
        Mockito.when(integral2.getType()).thenReturn((byte) 0);
        Mockito.when(integral2.getTime()).thenReturn(new Timestamp(System.currentTimeMillis()));
        
        Integral integral3 = Mockito.mock(Integral.class);
        Mockito.when(integral3.getUserId()).thenReturn(Math.random()+"");
        Mockito.when(integral3.getCount()).thenReturn(100);
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
	    
		impl.add(holder);
	}
	
	@Test
	public void testMongo(){
		MongoTemplate template = context.getBean(MongoTemplate.class);
		SimpleMapObject sm = new SimpleMapObject();
        sm.put("typeName","教育");
//    	List<Map<String, Object>> map = template.find("task", sm);
//    	System.out.println(map);
//    	for (Map<String, Object> map2 : map) {
//			System.out.println(map2);
//		}
        
        Map<String, Object> map = template.findOne("task", sm);
    	System.out.println(map);
	}
	
	@Test
	public void testFindTask(){
		MongoTemplate template = context.getBean(MongoTemplate.class);
		SimpleMapObject sm = new SimpleMapObject();
        sm.put("orderNO","14468118502374438320");
		Map<String, Object> map = template.findOne("payOrder", sm);
		System.out.println(map);
	}
	
	@Test
	public void testSingleton(){
	    ThreadPoolFactory tf1=context.getBean(ThreadPoolFactory.class);
	    ThreadPoolFactory tf2=context.getBean(ThreadPoolFactory.class);
	    System.out.println(tf1==tf2);
	}
}
