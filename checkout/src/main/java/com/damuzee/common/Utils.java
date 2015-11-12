package com.damuzee.common;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

public class Utils {
	public static <T> boolean isNull(T t){
	    if(t == null){
	        return true;
	    }
		if(t instanceof Map<?,?>){
			Map<?,?> map =(Map<?, ?>) t;
			return map==null || ((Map<?,?>) t).isEmpty();
		}else if(t instanceof String){
			String str = (String) t;
			return str==null || str.trim().equals("");
		}
        return false;
	}
	
	public static Timestamp getCurrentTime(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static BigDecimal ratioExchange(int percent,int base){
	    BigDecimal bonusBigDecimal = new BigDecimal(percent);
        BigDecimal baseBigDecimal = new BigDecimal(base);
//		四舍五入，使用银行家算法让结果更准确
        return bonusBigDecimal.divide(baseBigDecimal).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public static BigDecimal computeBonus(BigDecimal total,BigDecimal ratio){
//		四舍五入，使用银行家算法让结果更准确
	    return total.multiply(ratio).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public static int computeIntegral(BigDecimal bonus,BigDecimal ratio,BigDecimal conversion){
//		四舍五入，使用银行家算法让结果更准确
        int result = bonus.multiply(ratio).multiply(conversion).setScale(0, BigDecimal.ROUND_HALF_EVEN).intValueExact();
	    return result;
	}
	
}
