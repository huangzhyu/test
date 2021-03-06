package com.damuzee.model;

import java.sql.Timestamp;
import com.damuzee.common.Checkout;


public class Integral {
    private String userId;
    private Timestamp time;
    private long count;
    private int ratio;
    private String orderId;
    private byte type;
    private int conversion;
    
    
    public Integral() {
        super();
    }

    public Integral(Member member) {
        this.userId=member.getUserId();
        this.ratio=member.getRatio();
        this.type=member.getOperationType();
        this.orderId=member.getOrderId();
    }

    /**
     * 
     * @return 用户编号
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * 设置用户编号
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 
     * @return 积分时间
     */
    public Timestamp getTime() {
        return time;
    }
    /**
     * 设置积分成功时间
     * @param time 积分成功时间
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    /**
     * 
     * @return 积分数
     */
    public long getCount() {
        return count;
    }
    
    /**
     * 设置积分数
     * @param count 积分数
     */
    public void setCount(long count) {
        this.count = count;
    }
    
    /**
     * 按照多少比例获得了这个积分
     * @return 本次积分占比
     */
    public int getRatio() {
        return ratio;
    }
    
    /**
     * 设置积分比例
     * @param ratio 本次积分占比
     */
    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
    /**
     * 产生这次积分的原始订单号
     * @return 订单号
     */
    public String getOrderId() {
        return orderId;
    }
    
    /**
     * 设置产生这次积分的原始订单号
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    /**
     * 积分类型
     * @return 0：收入；1：支出
     */
    public byte getType() {
        return type;
    }
    /**
     * 设置积分类型
     * @param type 0：收入；1：支出 
     */
    public void setType(Checkout type) {
        if(Checkout.INCOME.equals(type)){
            this.type=0;
        }else if(Checkout.PAY.equals(type)){
            this.type = 1;
        }else{
            throw new IllegalStateException("Only [Operation.PAY] or [Operation.INCOME] supproted.");
        }
        
    }
    

	public int getConversion() {
		return conversion;
	}

	/**
	 * 设置积分换算系数
	 * @param conversion 积分换算系数
	 */
	public void setConversion(int conversion) {
		this.conversion = conversion;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(50);
		sb.append("[userId=").append(userId).append(" time=").append(time).append(" count=")
		.append(count).append(" ratio=").append(ratio).append(" orderId=").append(orderId)
		.append(" type=").append(type==0?Checkout.INCOME:Checkout.PAY)
		.append(" conversion=").append(conversion).append(" ]");
		return sb.toString();
	}
    
    
    
}
