package com.damuzee.model;

import java.util.List;
import java.util.Map;

import org.damuzee.mongo.MongoTemplate;
import org.damuzee.mongo.SimpleMapObject;
import org.damuzee.mongo.factory.SimpleMongoFactory;
import org.junit.Test;

public class MongDbTest {

    MongoTemplate template;

    @Test
    public void testFindById (){
    	SimpleMongoFactory factory = new SimpleMongoFactory();
    	factory.setHost("123.57.177.111:27017");
    	factory.setPort(27017);
//    	factory.setAuthentication(false);
    	factory.setDbName("meilin");
    	template = new MongoTemplate(factory);
        SimpleMapObject sm = new SimpleMapObject();
        sm.put("typeName","教育");
    	List<Map<String, Object>> map = template.find("task", sm);
    	System.out.println(map);
    	for (Map<String, Object> map2 : map) {
			System.out.println(map2);
		}
    }
    
    
}
