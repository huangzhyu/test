package com.damuzee.common;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
//	    MathContext mc  = new MathContext(Constant.SCALE,RoundingMode.HALF_EVEN); 
	    BigDecimal bonusBigDecimal = new BigDecimal(percent);
        BigDecimal baseBigDecimal = new BigDecimal(base);
        return bonusBigDecimal.divide(baseBigDecimal).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public static BigDecimal computeBonus(BigDecimal total,BigDecimal ratio){
//	    MathContext mc  = new MathContext(Constant.SCALE,RoundingMode.HALF_EVEN); 
	    return total.multiply(ratio).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public static String computeIntegral(BigDecimal bonus,BigDecimal ratio,BigDecimal conversion){
//	    MathContext mc  = new MathContext(Constant.SCALE,RoundingMode.HALF_EVEN); 
        String result = bonus.multiply(ratio).multiply(conversion).setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString();
	    return result;
	}
}
