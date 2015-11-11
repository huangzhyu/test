package com.damuzee.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damuzee.executor.DivideTaskExecutor;
import com.damuzee.executor.Executor;
import com.damuzee.facade.CheckoutFacade;
import com.damuzee.model.Task;

public class CheckoutFacadeImpl implements CheckoutFacade {
	private static  final Logger logger = LoggerFactory.getLogger(CheckoutFacadeImpl.class);

	private Executor divideTaskExecutor;
	
	public void setDivideTaskExecutor(Executor divideTaskExecutor) {
	    if(!(divideTaskExecutor instanceof DivideTaskExecutor)){
	        throw new UnsupportedOperationException("Cannot use this kind of Executor: "+divideTaskExecutor);
	    }
        this.divideTaskExecutor = divideTaskExecutor;
    }

	@Override
	public void checkout(Task task) {
		logger.info("New task "+task+" added into the thread pool.");
		divideTaskExecutor.submit(task);
	}

}
