package com.damuzee.db;

import java.util.Map;

import org.damuzee.mongo.MongoTemplate;
import org.damuzee.mongo.SimpleMapObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.damuzee.model.Member;

public class TestMemberAccessImpl {
	ApplicationContext context = null;
	MemberAccessImpl ma=null;
    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("application-context.xml");
        ma = context.getBean(MemberAccessImpl.class);
    }
    
    @Test
    public void testGetMember(){
        Member member = ma.getFirst(new Member("14468118502374438320"));
        System.out.println(member);
    }
    
    @Test
    public void getSuperMember(){
        Member member = ma.getFirst(new Member("14468118502374438320"));
        System.out.println(member);
        Member result=ma.getSuperiorMember(member);
        System.out.println(result);
        Member result2=ma.getSuperiorMember(result);
        System.out.println(result2);
    }
    

	@Test
	public void testMongo(){
		MongoTemplate template = context.getBean(MongoTemplate.class);
		SimpleMapObject sm = new SimpleMapObject();
        sm.put("typeName","家政");
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
    
}
