package com.damuzee.executor;

import java.util.concurrent.CompletionService;

import com.damuzee.model.Mission;
import com.damuzee.model.ResultHolder;
import com.damuzee.model.Task;
import com.damuzee.strategy.StrategyContext;


public abstract class Executor {
	protected CompletionService<ResultHolder> threadPool;
	protected StrategyContext strategyContext;

	/**
	 * 往线程池中提交任务
	 * @param task
	 */
	public void submit(Task task){
		threadPool.submit(new Mission(strategyContext, task));
	}
	
	/**
	 * 获取线程池中已经执行完成的任务的结果，进行下一步处理
	 */
	public abstract void onComplete();
}
