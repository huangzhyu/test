package com.damuzee.strategy;

import com.damuzee.model.ResultHolder;

public class StrategyContext<T> {
	private Strategy<T> strategy;

	public StrategyContext(Strategy<T> strategy){
	      this.strategy = strategy;
	   }
	
	public void setStrategy(Strategy<T> strategy){
		this.strategy=strategy;
	}

	public ResultHolder executeStrategy(T entity) {
		return strategy.doOperation(entity);
	}
}
