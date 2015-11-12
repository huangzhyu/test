package com.damuzee.executor;

import java.util.concurrent.CompletionService;

import com.damuzee.model.Mission;
import com.damuzee.model.ResultHolder;
import com.damuzee.strategy.StrategyContext;


public abstract class Executor<T> {
	protected CompletionService<ResultHolder> threadPool;
	protected StrategyContext<T> strategyContext;

	/**
	 * 往线程池中提交任务
	 * @param task
	 */
	public void submit(T task){
		threadPool.submit(new Mission<T>(strategyContext, task));
	}
	
	/**
	 * 获取线程池中已经执行完成的任务的结果，进行下一步处理
	 */
	public abstract void onComplete();
}
