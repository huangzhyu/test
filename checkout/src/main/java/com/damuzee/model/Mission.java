package com.damuzee.model;

import java.util.concurrent.Callable;

import com.damuzee.strategy.StrategyContext;

public class Mission implements Callable<ResultHolder> {

	private StrategyContext context;
	private Task entity;
	
	public Mission(StrategyContext context,Task entity) {
		this.context = context;
		this.entity = entity;
	}
	
	public void setContext(StrategyContext context) {
		this.context = context;
	}

	public void setEntity(Task entity) {
		this.entity = entity;
	}

	@Override
	public ResultHolder call() throws Exception {
		return context.executeStrategy(entity);
	}


}
