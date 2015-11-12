package com.damuzee.model;

import java.util.concurrent.Callable;

import com.damuzee.strategy.StrategyContext;

public class Mission<T> implements Callable<ResultHolder> {

	private StrategyContext<T> context;
	private T entity;
	
	public Mission(StrategyContext<T> context,T entity) {
		this.context = context;
		this.entity = entity;
	}
	
	public void setContext(StrategyContext<T> context) {
		this.context = context;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	@Override
	public ResultHolder call() throws Exception {
		return context.executeStrategy(entity);
	}


}
