package com.damuzee.service;

import java.util.List;

import com.damuzee.common.Retry;
import com.damuzee.db.DataAccess;
import com.damuzee.model.FailedOrder;
import com.damuzee.model.ResultHolder;

public class IntegralService {
	private DataAccess<ResultHolder> integralAccess;
	private DataAccess<FailedOrder> orderAccess;
	
	public DataAccess<ResultHolder> getIntegralAccess() {
		return integralAccess;
	}
	public void setIntegralAccess(DataAccess<ResultHolder> integralAccess) {
		this.integralAccess = integralAccess;
	}
	public DataAccess<FailedOrder> getOrderAccess() {
		return orderAccess;
	}
	public void setOrderAccess(DataAccess<FailedOrder> orderAccess) {
		this.orderAccess = orderAccess;
	}
	
	
	public void add(ResultHolder holder) {
		if (!integralAccess.add(holder)) {
			FailedOrder order = new FailedOrder();
			order.setOrderId(holder.getOrderId());
			order.setTime(holder.getTime());
			order.setType(holder.getType());
			order.setStatus((byte) Retry.RETRY.ordinal());
			add(order);
		}else{
		    FailedOrder order = new FailedOrder();
            order.setOrderId(holder.getOrderId());
		    orderAccess.delete(order);
		}
	}
	
	public final void add(FailedOrder order){
	    orderAccess.add(order);
	}
	
	public List<FailedOrder> getFailedOrder(FailedOrder order){
	    return orderAccess.getALL(order);
	}
}
