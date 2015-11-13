package com.damuzee.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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
		new Thread(new Runnable() {
			@Override
			public void run() {
				DivideTaskExecutor.this.onComplete();
			}
		}).start();
	}

	@Override
	public void onComplete(){
		//task分解成功，将每个task添加到更新数据的线程池中等待执行
		Future<ResultHolder> holder = null;
		try {
			for (;;) {
			    holder = this.threadPool.poll(100,TimeUnit.MILLISECONDS);
			    if(holder==null || holder.get()==null){
//			    	System.out.println("no result yet.");
			    	continue;
			    }
				
				checkoutExecutor.submit(holder.get());
			}
		} catch (InterruptedException e) {
			logger.error("Thread interruped when take finished task");
			if(holder != null){
				holder.cancel(true);
			}
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
            e.printStackTrace();
        }
		
	}
}
