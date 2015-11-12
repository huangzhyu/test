package com.damuzee.model;

import java.sql.Timestamp;

import com.damuzee.common.Operation;

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
     * 获取当前order的状态
     * 0：积分成功
     * 1：等待重试
     */
    public byte getType() {
        return type;
    }
    /**
     * 设置order状态
     * @param type 0：积分成功;1:等待重试
     */
    public void setType(Operation type) {
        if(Operation.SUCCESS.equals(type)){
            this.type=0;
        }else if(Operation.RETRY.equals(type)){
            this.type=1;
        }else{
            throw new IllegalStateException("Only [Operation.SUCCESS] or [Operation.RETRY] supproted.");
        }
    }
}
