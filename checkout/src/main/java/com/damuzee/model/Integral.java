package com.damuzee.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.damuzee.common.Operation;


public class Integral {
    private String userId;
    private Timestamp time;
    private int count;
    private int ratio;
    private String orderId;
    private byte type;
    private List<Integral> parents;
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
    public int getCount() {
        return count;
    }
    
    /**
     * 设置积分数
     * @param count 积分数
     */
    public void setCount(int count) {
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
    public void setType(Operation type) {
        if(Operation.INCOME.equals(type)){
            this.type=0;
        }else if(Operation.PAY.equals(type)){
            this.type = 1;
        }else{
            throw new IllegalStateException("Only [Operation.PAY] or [Operation.INCOME] supproted.");
        }
        
    }

    /**
     * 
     * @return 包含了当前用户的上线应得的积分
     */
    public List<Integral> getParents() {
        return parents==null ? new ArrayList<Integral>() : parents;
    }

    /**
     * 设置当前用户的上线应得的积分
     * @param parents 上线应得积分的集合
     */
    public void setParents(List<Integral> parents) {
        this.parents = parents;
    }
    
    
}
