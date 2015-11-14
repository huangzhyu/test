package com.damuzee.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.damuzee.model.Member;
import com.damuzee.model.ResultHolder;
import com.damuzee.strategy.Strategy;
import com.damuzee.strategy.StrategyContext;

public class DivideTaskExecutor extends Executor<Member> {
	private static  final Logger logger = LoggerFactory.getLogger(DivideTaskExecutor.class);
	private Executor<ResultHolder> checkoutExecutor;
	@Autowired
	public DivideTaskExecutor(Executor<ResultHolder> checkoutExecutor,Strategy<Member> divideTaskStrategy,ThreadPoolFactory threadPoolFactory) {
		threadPool=threadPoolFactory.getDivideTaskThreadPool();
		strategyContext = new StrategyContext<Member>(divideTaskStrategy);
		this.checkoutExecutor = checkoutExecutor;
	}
	
	@PostConstruct 
	public void init(){
		Executors.newSingleThreadExecutor(new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("Retrieve divided ResultHolder");
				return thread;
			}
		}).submit(new Runnable() {
			@Override
			public void run() {
				onComplete();
			}
		});
	}

	@Override
	public void onComplete(){
		//task分解成功，将每个task添加到更新数据的线程池中等待执行
		Future<ResultHolder> future = null;
		try {
			for (;;) {
				future = this.threadPool.poll(100,TimeUnit.MILLISECONDS);
			    if(future==null || future.get()==null){
			    	continue;
			    }
				checkoutExecutor.submit(future.get());
			}
		} catch (InterruptedException e) {
			if(future!=null){
				future.cancel(true);
			}
			logger.error("Thread interruped when take finished task");
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
            e.printStackTrace();
        }
		
	}
}
