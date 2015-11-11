package com.damuzee.facade.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.damuzee.common.Operation;
import com.damuzee.common.Utils;

public class AppTest {

    public static void main(String[] args) {
//       System.out.println(Operation.RETRY.equals(Operation.INCOME));
//       Timestamp ts = new Timestamp(System.currentTimeMillis());
//       System.out.println(ts);
//       
//       Map<String,String> map = new HashMap<String,String>();
//       System.out.println(Utils.isNull(map));
//       map.put("1", "a");
//       System.out.println(Utils.isNull(map));
//       System.out.println(Utils.isNull(null));
//       System.out.println(Utils.isNull(""));
//       System.out.println(Utils.isNull("   "));
//       System.out.println(Utils.isNull("abc"));
    	
    	Object obj = null;
    	System.out.println(Utils.isNull(obj));
    	obj=new Object();
    	System.out.println(Utils.isNull(obj));
    }

}
