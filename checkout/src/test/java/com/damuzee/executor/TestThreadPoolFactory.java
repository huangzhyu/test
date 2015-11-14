package com.damuzee.executor;

import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestThreadPoolFactory {
	ApplicationContext context = null;
	ThreadPoolFactory factory;
	Properties prop = null;
    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("application-context.xml");
        factory = context.getBean(ThreadPoolFactory.class);
        prop = new Properties();
        try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testCreateFactory(){
    	System.out.println(prop);
    }
    
}
