package com.damuzee.executor;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.damuzee.model.ResultHolder;
import com.damuzee.strategy.Strategy;
import com.damuzee.strategy.StrategyContext;

public class CheckoutExecutor extends Executor<ResultHolder> {
//	private static  final Logger logger = LoggerFactory.getLogger(CheckoutExecutor.class);
	@Autowired
	public CheckoutExecutor(Strategy<ResultHolder> checkoutStrategy,ThreadPoolFactory threadPoolFactory) {
	    this.threadPool = threadPoolFactory.getCheckoutThreadPool();
		strategyContext = new StrategyContext<ResultHolder>(checkoutStrategy);
	}

//	@PostConstruct 
	public void init() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				onComplete();
//			}
//		}).start();

	}
//	private static class ThreadPoolHolder{
//		public static CompletionService<ResultHolder> threadPool = ThreadPool.INSTANCE.getUpdateTaskThreadPool();
//	}

	/**
	 * 当前没有用到这个方法
	 */
	@Override
	public void onComplete() {
//		数据更新完成,如果针对每个完成的task有后续操作要执行，可以放在这里
		
		try {
            for(;;){
                Future<ResultHolder> holder;
                    holder = threadPool.poll(100,TimeUnit.MILLISECONDS);
                    System.out.println("holder="+holder);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            //TODO
        }

	}
}
