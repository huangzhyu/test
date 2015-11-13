package com.damuzee.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import com.damuzee.model.FailedOrder;
import com.damuzee.model.Member;
import com.damuzee.model.Mission;
import com.damuzee.model.ResultHolder;
import com.damuzee.service.IntegralService;

public class RejectedHandler implements RejectedExecutionHandler {
    private IntegralService service;
    private ExecutorService alternateExecutor = Executors.newCachedThreadPool();
    
    public IntegralService getService() {
        return service;
    }

    public void setService(IntegralService service) {
        this.service = service;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        Mission<?> mission = (Mission<?>) r;
        
        if(mission.getEntity() instanceof Member){
            //DivideTaskExecutor 拒绝任务，写入unsuccess表
            Member m = (Member) mission.getEntity();
            FailedOrder order = new FailedOrder();
            order.setOrderId(m.getOrderId());
            service.add(order);
            
        }else if(mission.getEntity() instanceof ResultHolder){
            //CheckoutExecutor 拒绝任务
            alternateExecutor.execute(r);
        }
    }

}