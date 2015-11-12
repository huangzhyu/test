package com.damuzee.model;

import java.sql.Timestamp;

public class FailedOrder {
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 记录失败时间
     */
    private Timestamp time;
    
    private byte type;
    
    private byte status;
    
    /**
     * 
     * @return 订单号
     */
    public String getOrderId() {
        return orderId;
    }
    /**
     * 设置订单号
     * @param orderId 订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    /**
     * @return 积分失败时的时间
     */
    public Timestamp getTime() {
        return time;
    }
    
    /**
     * 设置时间
     * @param time 积分失败时间
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    /**
     * 本次积分的类型是兑换还是增加
     * @return
     */
    public byte getType() {
        return type;
    }
    
    public void setType(byte type){
    	this.type=type;
    }
    
    /**
     * 获取当前order的状态
     * 0：积分成功
     * 1：等待重试
     */
	public byte getStatus() {
		return status;
	}
	
	/**
     * 设置当前order的状态
     * 0：积分成功
     * 1：等待重试
     */
	public void setStatus(byte status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "FailedOrder [orderId=" + orderId + ", time=" + time + ", type=" + type + ", status=" + status + "]";
	}
    
    
    /**
     * 设置order状态
     * @param type 0：积分成功;1:等待重试
     */
//    public void setType(Operation type) {
//        if(Operation.SUCCESS.equals(type)){
//            this.type=0;
//        }else if(Operation.RETRY.equals(type)){
//            this.type=1;
//        }else{
//            throw new IllegalStateException("Only [Operation.SUCCESS] or [Operation.RETRY] supproted.");
//        }
//    }
	
	
}
