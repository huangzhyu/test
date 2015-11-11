package com.damuzee.common;

import java.sql.Timestamp;
import java.util.Map;

public class Utils {
	@SuppressWarnings("rawtypes")
	public static <T> boolean isNull(T t){
		if(t instanceof Map){
			Map<?,?> map =(Map<?, ?>) t;
			return map==null || ((Map) t).isEmpty();
		}else if(t instanceof String){
			String str = (String) t;
			return str==null || str.trim().equals("");
		}
		return t==null;
	}
	
	public static Timestamp getCurrentTime(){
		return new Timestamp(System.currentTimeMillis());
	}
}
