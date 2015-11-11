package com.damuzee.executor;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.damuzee.model.ResultHolder;
import com.damuzee.model.Task;
import com.damuzee.strategy.Strategy;
import com.damuzee.strategy.StrategyContext;

public class DivideTaskExecutor extends Executor {
	private static  final Logger logger = LoggerFactory.getLogger(DivideTaskExecutor.class);
	private Executor checkoutExecutor;
	
	@Autowired
	public DivideTaskExecutor(Executor checkoutExecutor,Strategy divideTaskStrategy) {
		threadPool=DivideTaskExecutor.ThreadPoolHolder.threadPool;
		strategyContext = new StrategyContext(divideTaskStrategy);
		this.checkoutExecutor = checkoutExecutor;
	}
	
	@PostConstruct 
	public void init(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				DivideTaskExecutor.this.onComplete();
			}
		}).start();
	}

	private static class ThreadPoolHolder{
		public static CompletionService<ResultHolder> threadPool = ThreadPool.INSTANCE.getDivideTaskThreadPool();
	}

	@Override
	public void onComplete(){
		//task分解成功，将每个task添加到更新数据的线程池中等待执行
		/**
		 * 特殊情况：
		 * 1:同一个task包含多个下级task，而这些下级task又同时产生了交易，此时分解线程池会产生多个上级task的对象，并提交到执行数据更新的
		 * 线程池中
		 */
		Future<ResultHolder> holder = null;
		try {
			for (;;) {
			    holder = this.threadPool.poll(1000,TimeUnit.MILLISECONDS);
			    if(holder==null){
			    	System.out.println("no result yet.");
			    	continue;
			    }
				List<Task> tasks = holder.get().getTasks();
				if (tasks != null && !tasks.isEmpty()) {
					for (Task task : tasks) {
						checkoutExecutor.submit(task);
						logger.info("task added to checkout thread pool");
					}
				}

			}
		} catch (InterruptedException e) {
			logger.error("Thread interruped when take finished task");
			if(holder != null){
				holder.cancel(true);
			}
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new IllegalStateException("computation error", e);
		}
		
	}
}
