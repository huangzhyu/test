package com.damuzee.strategy;

import com.damuzee.model.ResultHolder;
import com.damuzee.model.Task;

public class StrategyContext {
	private Strategy strategy;

	public StrategyContext(Strategy strategy){
	      this.strategy = strategy;
	   }
	
	public void setStrategy(Strategy strategy){
		this.strategy=strategy;
	}

	public ResultHolder executeStrategy(Task entity) {
		return strategy.doOperation(entity);
	}
}
