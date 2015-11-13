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
    private int delayHours;
    @Autowired
    public ScheduledExecutor(ThreadPoolFactory threadPoolFactory,Strategy<FailedOrder> retryFailedOrderStrategy){
        ses = threadPoolFactory.getScheduled();
        context = new StrategyContext<FailedOrder>(retryFailedOrderStrategy);
    }
    
    public int getDelayHours() {
        return delayHours;
    }

    public void setDelayHours(int delayHours) {
        this.delayHours = delayHours;
    }
    
    public void start(){
        final StrategyContext<FailedOrder> ctx = context;
        ses.schedule(new Runnable(){
            @Override
            public void run() {
                FailedOrder order = new FailedOrder();
                order.setStatus((byte) 1);
               ctx.executeStrategy(order);
            }}, delayHours*60*60, TimeUnit.SECONDS);
    }
}
