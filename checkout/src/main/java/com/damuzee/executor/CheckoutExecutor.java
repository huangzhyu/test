package com.damuzee.executor;

import org.springframework.beans.factory.annotation.Autowired;

import com.damuzee.model.ResultHolder;
import com.damuzee.strategy.Strategy;
import com.damuzee.strategy.StrategyContext;

public class CheckoutExecutor extends Executor<ResultHolder> {
	@Autowired
	public CheckoutExecutor(Strategy<ResultHolder> checkoutStrategy,ThreadPoolFactory threadPoolFactory) {
	    this.threadPool = threadPoolFactory.getCheckoutThreadPool();
		strategyContext = new StrategyContext<ResultHolder>(checkoutStrategy);
	}
}
