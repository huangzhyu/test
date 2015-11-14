package com.damuzee.executor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.damuzee.model.FailedOrder;
import com.damuzee.strategy.Strategy;
import com.damuzee.strategy.StrategyContext;

public class ScheduledExecutor {
    private ScheduledExecutorService ses;
    private StrategyContext<FailedOrder> context;
    private int delaySeconds;
    @Autowired
    public ScheduledExecutor(ThreadPoolFactory threadPoolFactory,Strategy<FailedOrder> retryFailedOrderStrategy){
        ses = threadPoolFactory.getScheduled();
        context = new StrategyContext<FailedOrder>(retryFailedOrderStrategy);
    }
    
    
    public void setDelaySeconds(int delaySeconds) {
		this.delaySeconds = delaySeconds;
	}


	public void start(){
        final StrategyContext<FailedOrder> ctx = context;
        ses.schedule(new Runnable(){
            @Override
            public void run() {
                FailedOrder order = new FailedOrder();
                order.setStatus((byte) 1);
               ctx.executeStrategy(order);
            }}, delaySeconds, TimeUnit.SECONDS);
    }
}
