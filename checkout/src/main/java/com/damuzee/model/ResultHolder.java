package com.damuzee.model;

import java.util.List;


public class ResultHolder {
	private List<Integral> Integrals;
	private String orderId;
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
}
