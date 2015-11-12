package com.damuzee.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damuzee.common.Operation;
import com.damuzee.executor.DivideTaskExecutor;
import com.damuzee.executor.Executor;
import com.damuzee.facade.CheckoutFacade;
import com.damuzee.model.Member;

public class CheckoutFacadeImpl implements CheckoutFacade {
	private static  final Logger logger = LoggerFactory.getLogger(CheckoutFacadeImpl.class);

	private Executor<Member> divideTaskExecutor;
	
	public void setDivideTaskExecutor(Executor<Member> divideTaskExecutor) {
	    if(!(divideTaskExecutor instanceof DivideTaskExecutor)){
	        throw new UnsupportedOperationException("Cannot use this kind of Executor: "+divideTaskExecutor);
	    }
        this.divideTaskExecutor = divideTaskExecutor;
    }

	@Override
	public void checkout(String orderId) {
	    Member member = new Member(orderId);
	    member.setOperationType(Operation.INCOME);
		divideTaskExecutor.submit(member);
		logger.info("New order "+orderId+" added into the divide Task thread pool.");
	}

    @Override
    public void exchange(Object obj) {
        
    }

}
