package com.damuzee.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damuzee.common.Checkout;
import com.damuzee.executor.DivideTaskExecutor;
import com.damuzee.executor.Executor;
import com.damuzee.executor.ScheduledExecutor;
import com.damuzee.facade.CheckoutFacade;
import com.damuzee.model.Member;
import com.damuzee.service.IntegralService;

public class CheckoutFacadeImpl implements CheckoutFacade {
	private static  final Logger logger = LoggerFactory.getLogger(CheckoutFacadeImpl.class);

	private Executor<Member> divideTaskExecutor;
	private IntegralService integralService;
	
	
	
	public void setIntegralService(IntegralService integralService) {
		this.integralService = integralService;
	}

	public void setDivideTaskExecutor(Executor<Member> divideTaskExecutor) {
	    if(!(divideTaskExecutor instanceof DivideTaskExecutor)){
	        throw new UnsupportedOperationException("Cannot use this kind of Executor: "+divideTaskExecutor);
	    }
        this.divideTaskExecutor = divideTaskExecutor;
    }
	
	public void setScheduledExecutor(ScheduledExecutor scheduledExecutor) {
        scheduledExecutor.start();
    }

    @Override
	public void checkout(String orderId,Checkout operation) {
	    Member member = new Member(orderId,(byte) operation.ordinal());
		divideTaskExecutor.submit(member);
		logger.info("New order "+orderId+" added into the divide Task thread pool.");
	}

	@Override
	public long getIntegral(String userId) {
		return integralService.getIntegral(userId);
	}

	@Override
	public boolean exchange(long integral,String userId) {
		return integralService.exchange(integral,userId);
	}

}
