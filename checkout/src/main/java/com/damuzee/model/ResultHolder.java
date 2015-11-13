package com.damuzee.model;

import java.sql.Timestamp;
import java.util.List;


public class ResultHolder {
	private List<Integral> Integrals;
	private String orderId;
	private byte type;
	private Timestamp time;
    public List<Integral> getIntegrals() {
        return Integrals;
    }
    public void setIntegrals(List<Integral> integrals) {
        Integrals = integrals;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "ResultHolder [orderId=" + orderId + ", type=" + type + ", time=" + time + "]";
	}
}
